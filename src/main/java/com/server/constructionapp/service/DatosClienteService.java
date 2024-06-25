package com.server.constructionapp.service;

import com.server.constructionapp.DTO.DatosClienteDTO;
import com.server.constructionapp.DTO.PrerequisitoPlanoDTO;
import com.server.constructionapp.model.Cliente;
import com.server.constructionapp.model.DatosCliente;
import com.server.constructionapp.model.PrerequisitoPlano;
import com.server.constructionapp.repository.ClienteRepo;
import com.server.constructionapp.repository.DatosClienteRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatosClienteService {
    @Autowired
    private DatosClienteRepo datosClienteRepo;
    @Autowired
    private ClienteRepo clienteRepo;

    public DatosClienteDTO crearDatosCliente(DatosClienteDTO datosClienteDTO) {
        DatosCliente datosCliente = mapToEntity(datosClienteDTO);
        DatosCliente nuevosDatosCliente = datosClienteRepo.save(datosCliente);
        return mapToDTO(nuevosDatosCliente);
    }

    public List<DatosClienteDTO> obtenerTodosLosDatosCliente() {
        List<DatosCliente> datosClientes = datosClienteRepo.findAll();
        return datosClientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public DatosClienteDTO obtenerDatosCliente(Long id) {
        DatosCliente datosCliente = datosClienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DatosCliente no encontrados con ID: " + id));
        return mapToDTO(datosCliente);
    }
    public DatosClienteDTO actualizarDatosCliente(DatosClienteDTO datosClienteDTO) {
        Long id = datosClienteDTO.getId();
        DatosCliente datosClienteExistente = datosClienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DatosCliente no encontrado con ID: " + id));

        datosClienteExistente.setGroundDirection(datosClienteDTO.getGroundDirection());
        datosClienteExistente.setLandArea(datosClienteDTO.getLandArea());
        datosClienteExistente.setTypeConstruction(datosClienteDTO.getTypeConstruction());
        if (datosClienteDTO.getPropertyDoc() != null) {
            datosClienteExistente.setPropertyDoc(datosClienteDTO.getPropertyDoc());
        }

        DatosCliente datosClienteActualizado = datosClienteRepo.save(datosClienteExistente);
        return mapToDTO(datosClienteActualizado);
    }

    public List<DatosClienteDTO> obtenerDatosClienteNoVinculados() {
        List<DatosCliente> datosClientes = datosClienteRepo.findByContratoPlanoIsNull();
        return datosClientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void eliminarDatosCliente(Long id) {
        datosClienteRepo.deleteById(id);
    }

    public List<DatosClienteDTO> obtenerPropiedadesPorId(Long clienteId){
        List<DatosCliente> datosClientes = datosClienteRepo.findByClienteId(clienteId);
        return datosClientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private DatosClienteDTO mapToDTO(DatosCliente datosCliente) {
        DatosClienteDTO dto = new DatosClienteDTO();
        dto.setId(datosCliente.getId());
        dto.setGroundDirection(datosCliente.getGroundDirection());
        dto.setLandArea(datosCliente.getLandArea());
        dto.setTypeConstruction(datosCliente.getTypeConstruction());
        dto.setPropertyDoc(datosCliente.getPropertyDoc());

        Cliente cliente = datosCliente.getCliente();
        if (cliente != null) {
            dto.setClienteId(cliente.getId());
        }

        return dto;
    }

    private DatosCliente mapToEntity(DatosClienteDTO dto) {
        DatosCliente datosCliente = new DatosCliente();
        datosCliente.setId(dto.getId());
        datosCliente.setGroundDirection(dto.getGroundDirection());
        datosCliente.setLandArea(dto.getLandArea());
        datosCliente.setTypeConstruction(dto.getTypeConstruction());
        datosCliente.setPropertyDoc(dto.getPropertyDoc());

        Long clienteId = dto.getClienteId();
        if (clienteId != null) {
            Cliente cliente = clienteRepo.findById(clienteId)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));
            datosCliente.setCliente(cliente);
        }

        return datosCliente;
    }
}
