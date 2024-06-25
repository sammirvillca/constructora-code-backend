package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.ClienteDTO;
import com.server.constructionapp.exception.ResourceNotFoundException;
import com.server.constructionapp.model.Cliente;
import com.server.constructionapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000", "https://constructora-code-frontend.vercel.app"})
@RestController
@RequestMapping("/api/v2")
public class ClienteREST {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClientes() {
        return clienteService.obtenerTodosLosClientes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese id no existe: " + id));
        return ResponseEntity.ok(convertToDto(cliente));
    }

    @PostMapping("/cliente")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(convertToDto(nuevoCliente), HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese id no existe: " + id));

        cliente.setFullName(clienteDTO.getFullName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPhone(clienteDTO.getPhone());
        cliente.setAddress(clienteDTO.getAddress());
        cliente.setCodCliente(clienteDTO.getCodCliente());

        Cliente updateCliente = clienteService.actualizarCliente(cliente);
        return ResponseEntity.ok(convertToDto(updateCliente));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un cliente con el id:" + id));

        clienteService.eliminarCliente(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    private ClienteDTO convertToDto(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setFullName(cliente.getFullName());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setPhone(cliente.getPhone());
        clienteDTO.setAddress(cliente.getAddress());
        clienteDTO.setCodCliente(cliente.getCodCliente());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setFullName(clienteDTO.getFullName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPhone(clienteDTO.getPhone());
        cliente.setAddress(clienteDTO.getAddress());
        cliente.setCodCliente(clienteDTO.getCodCliente());
        return cliente;
    }

}
