package com.server.constructionapp.service;

import com.server.constructionapp.DTO.ContratoPlanoDTO;
import com.server.constructionapp.model.ContratoPlano;
import com.server.constructionapp.model.DatosCliente;
import com.server.constructionapp.model.DibujoPlano;
import com.server.constructionapp.repository.ContratoPlanoRepo;
import com.server.constructionapp.repository.DatosClienteRepo;
import com.server.constructionapp.repository.DibujoPlanoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoPlanoService {
    @Autowired
    private ContratoPlanoRepo contratoPlanoRepo;
    @Autowired
    private DatosClienteRepo datosClienteRepo;
    @Autowired
    private DibujoPlanoRepo dibujoPlanoRepo;

    public ContratoPlanoDTO crearContratoPlano(ContratoPlanoDTO contratoPlanoDTO) {
        ContratoPlano contratoPlano = mapToEntity(contratoPlanoDTO);
        ContratoPlano nuevoContratoPlano = contratoPlanoRepo.save(contratoPlano);
        return mapToDTO(nuevoContratoPlano);
    }

    public ContratoPlanoDTO obtenerContratoPlano(Long id) {
        ContratoPlano contratoPlano = contratoPlanoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContratoPlano no encontrado con ID: " + id));
        return mapToDTO(contratoPlano);
    }

    public List<ContratoPlanoDTO> obtenerTodosLosContratosPlano() {
        List<ContratoPlano> contratosPlano = contratoPlanoRepo.findAll();
        return contratosPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContratoPlanoDTO actualizarContratoPlano(ContratoPlanoDTO contratoPlanoDTO) {
        ContratoPlano contratoPlano = mapToEntity(contratoPlanoDTO);
        ContratoPlano contratoPlanoActualizado = contratoPlanoRepo.save(contratoPlano);
        return mapToDTO(contratoPlanoActualizado);
    }

    public void eliminarContratoPlano(Long id) {
        contratoPlanoRepo.deleteById(id);
    }

    private ContratoPlanoDTO mapToDTO(ContratoPlano contratoPlano) {
        ContratoPlanoDTO dto = new ContratoPlanoDTO();
        dto.setId(contratoPlano.getId());
        dto.setDescription(contratoPlano.getDescription());
        dto.setDeadlines(contratoPlano.getDeadlines());
        dto.setPrices(contratoPlano.getPrices());
        dto.setContractDocument(contratoPlano.getContractDocument());
        dto.setCodContrato(contratoPlano.getCodContrato());

        DatosCliente datosCliente = contratoPlano.getDatosCliente();
        if (datosCliente != null) {
            dto.setDatosClienteId(datosCliente.getId());
        }

        DibujoPlano dibujoPlano = contratoPlano.getDibujoPlano();
        if (dibujoPlano != null) {
            dto.setDibujoPlanoId(dibujoPlano.getId());
        }

        return dto;
    }

    private ContratoPlano mapToEntity(ContratoPlanoDTO dto) {
        ContratoPlano contratoPlano = new ContratoPlano();
        contratoPlano.setId(dto.getId());
        contratoPlano.setDescription(dto.getDescription());
        contratoPlano.setDeadlines(dto.getDeadlines());
        contratoPlano.setPrices(dto.getPrices());
        contratoPlano.setContractDocument(dto.getContractDocument());
        contratoPlano.setCodContrato(dto.getCodContrato());

        Long datosClienteId = dto.getDatosClienteId();
        if (datosClienteId != null) {
            DatosCliente datosCliente = datosClienteRepo.findById(datosClienteId)
                    .orElseThrow(() -> new EntityNotFoundException("DatosCliente no encontrado con ID: " + datosClienteId));
            contratoPlano.setDatosCliente(datosCliente);
        }

        Long dibujoPlanoId = dto.getDibujoPlanoId();
        if (dibujoPlanoId != null) {
            DibujoPlano dibujoPlano = dibujoPlanoRepo.findById(dibujoPlanoId)
                    .orElseThrow(() -> new EntityNotFoundException("DibujoPlano no encontrado con ID: " + dibujoPlanoId));
            contratoPlano.setDibujoPlano(dibujoPlano);
        }

        return contratoPlano;
    }
}
