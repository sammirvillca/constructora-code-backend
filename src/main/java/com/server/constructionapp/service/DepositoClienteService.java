package com.server.constructionapp.service;

import com.server.constructionapp.DTO.DepositoClienteDTO;
import com.server.constructionapp.model.DatosCliente;
import com.server.constructionapp.model.DepositoCliente;
import com.server.constructionapp.repository.DatosClienteRepo;
import com.server.constructionapp.repository.DepositoClienteRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositoClienteService {
    @Autowired
    private DepositoClienteRepo depositoClienteRepo;
    @Autowired
    private DatosClienteRepo datosClienteRepo;

    public DepositoClienteDTO crearDepositoCliente(DepositoClienteDTO depositoClienteDTO) {
        DepositoCliente depositoCliente = mapToEntity(depositoClienteDTO);
        DepositoCliente nuevoDepositoCliente = depositoClienteRepo.save(depositoCliente);
        return mapToDTO(nuevoDepositoCliente);
    }

    public DepositoClienteDTO obtenerDepositoCliente(Long id) {
        DepositoCliente depositoCliente = depositoClienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DepositoCliente no encontrado con ID: " + id));
        return mapToDTO(depositoCliente);
    }
    public List<DepositoClienteDTO> obtenerTodosLosDepositosCliente() {
        List<DepositoCliente> depositosCliente = depositoClienteRepo.findAll();
        return depositosCliente.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    public DepositoClienteDTO actualizarDepositoCliente(DepositoClienteDTO depositoClienteDTO) {
        Long id = depositoClienteDTO.getId();
        DepositoCliente depositoClienteExistente = depositoClienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DepositoCliente no encontrado con ID: " + id));

        depositoClienteExistente.setPayMethod(depositoClienteDTO.getPayMethod());
        if (depositoClienteDTO.getPayPhoto() != null) {
            depositoClienteExistente.setPayPhoto(depositoClienteDTO.getPayPhoto());
        }
        depositoClienteExistente.setPayDetails(depositoClienteDTO.getPayDetails());
        depositoClienteExistente.setPayDate(depositoClienteDTO.getPayDate());

        DepositoCliente depositoClienteActualizado = depositoClienteRepo.save(depositoClienteExistente);
        return mapToDTO(depositoClienteActualizado);
    }

    public void eliminarDepositoCliente(Long id) {
        depositoClienteRepo.deleteById(id);
    }

    public List<DepositoClienteDTO> obtenerDepositosPorId(Long datosClienteId){
        List<DepositoCliente> depositoClientes = depositoClienteRepo.findByDatosCliente_Id(datosClienteId);
        return depositoClientes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    private DepositoClienteDTO mapToDTO(DepositoCliente depositoCliente){
        DepositoClienteDTO dto = new DepositoClienteDTO();
        dto.setId(depositoCliente.getId());
        dto.setPayMethod(depositoCliente.getPayMethod());
        dto.setPayPhoto(depositoCliente.getPayPhoto());
        dto.setPayDetails(depositoCliente.getPayDetails());
        dto.setPayDate(depositoCliente.getPayDate());

        DatosCliente datosCliente = depositoCliente.getDatosCliente();
        if (datosCliente != null){
            dto.setDatosClienteId(datosCliente.getId());
        }
        return dto;
    }
    private DepositoCliente mapToEntity(DepositoClienteDTO dto) {
        DepositoCliente depositoCliente = new DepositoCliente();
        depositoCliente.setId(dto.getId());
        depositoCliente.setPayMethod(dto.getPayMethod());
        depositoCliente.setPayPhoto(dto.getPayPhoto());
        depositoCliente.setPayDetails(dto.getPayDetails());
        depositoCliente.setPayDate(dto.getPayDate());

        Long datosClienteId = dto.getDatosClienteId();
        if (datosClienteId != null) {
            DatosCliente datosCliente = datosClienteRepo.findById(datosClienteId)
                    .orElseThrow(() -> new EntityNotFoundException("DatosCliente no encontrado con ID: " + datosClienteId));
            depositoCliente.setDatosCliente(datosCliente);
        }

        return depositoCliente;
    }
}
