package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.DatosClienteDTO;
import com.server.constructionapp.DTO.PrerequisitoPlanoDTO;
import com.server.constructionapp.service.DatosClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://constructora-code-frontend.vercel.app"})
@RestController
@RequestMapping("/api/v2")
public class DatosClienteREST {
    @Autowired
    private DatosClienteService datosClienteService;

    @PostMapping(value = "/datos-cliente", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DatosClienteDTO> crearDatosCliente(
            @RequestParam("groundDirection") String groundDirection,
            @RequestParam("landArea") String landArea,
            @RequestParam("typeConstruction") String typeConstruction,
            @RequestParam("propertyDoc") MultipartFile propertyDoc,
            @RequestParam("clienteId") Long clienteId
    ) throws IOException {
        DatosClienteDTO datosClienteDTO = new DatosClienteDTO();
        datosClienteDTO.setGroundDirection(groundDirection);
        datosClienteDTO.setLandArea(landArea);
        datosClienteDTO.setTypeConstruction(typeConstruction);
        datosClienteDTO.setPropertyDoc(propertyDoc.getBytes());
        datosClienteDTO.setClienteId(clienteId);

        DatosClienteDTO nuevosDatosClienteDTO = datosClienteService.crearDatosCliente(datosClienteDTO);
        return new ResponseEntity<>(nuevosDatosClienteDTO, HttpStatus.CREATED);
    }

    @GetMapping("/datos-cliente/{id}")
    public ResponseEntity<DatosClienteDTO> obtenerDatosCliente(@PathVariable Long id) {
        DatosClienteDTO datosClienteDTO = datosClienteService.obtenerDatosCliente(id);
        return ResponseEntity.ok(datosClienteDTO);
    }

    @GetMapping("/datos-clientes")
    public ResponseEntity<List<DatosClienteDTO>> obtenerTodosLosDatosCliente() {
        List<DatosClienteDTO> datosClienteDTOs = datosClienteService.obtenerTodosLosDatosCliente();
        return ResponseEntity.ok(datosClienteDTOs);
    }

    @PutMapping(value = "/datos-clientes/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DatosClienteDTO> actualizarDatosCliente(
            @PathVariable Long id,
            @RequestParam("groundDirection") String groundDirection,
            @RequestParam("landArea") String landArea,
            @RequestParam("typeConstruction") String typeConstruction,
            @RequestParam(value = "propertyDoc", required = false) MultipartFile propertyDoc,
            @RequestParam("clienteId") Long clienteId
    ) throws IOException {
        DatosClienteDTO datosClienteDTO = new DatosClienteDTO();
        datosClienteDTO.setId(id);
        datosClienteDTO.setGroundDirection(groundDirection);
        datosClienteDTO.setLandArea(landArea);
        datosClienteDTO.setTypeConstruction(typeConstruction);
        if (propertyDoc != null && !propertyDoc.isEmpty()) {
            datosClienteDTO.setPropertyDoc(propertyDoc.getBytes());
        }
        datosClienteDTO.setClienteId(clienteId);

        DatosClienteDTO datosClienteActualizado = datosClienteService.actualizarDatosCliente(datosClienteDTO);
        return ResponseEntity.ok(datosClienteActualizado);
    }

    @DeleteMapping("/datos-clientes/{id}")
    public ResponseEntity<Void> eliminarDatosCliente(@PathVariable Long id) {
        datosClienteService.eliminarDatosCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes/propiedades/{clienteId}")
    public ResponseEntity<List<DatosClienteDTO>> obtenerPropiedadesClientePorClienteId(@PathVariable Long clienteId){
        List<DatosClienteDTO> datosClienteDTOS = datosClienteService.obtenerPropiedadesPorId(clienteId);
        return ResponseEntity.ok(datosClienteDTOS);
    }

    @GetMapping("/propiedades-no-vinculados")
    public ResponseEntity<List<DatosClienteDTO>> obtenerDatosClienteNoVinculados() {
        List<DatosClienteDTO> datosClienteDTOS = datosClienteService.obtenerDatosClienteNoVinculados();
        return ResponseEntity.ok(datosClienteDTOS);
    }



}
