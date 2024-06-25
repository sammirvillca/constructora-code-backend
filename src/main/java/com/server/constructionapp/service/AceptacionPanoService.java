package com.server.constructionapp.service;

import com.server.constructionapp.DTO.AceptacionPlanoDTO;
import com.server.constructionapp.DTO.DibujoPlanoDTO;
import com.server.constructionapp.model.AceptacionPlano;
import com.server.constructionapp.model.DibujoPlano;
import com.server.constructionapp.repository.AceptacionPlanoRepo;
import com.server.constructionapp.repository.DibujoPlanoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AceptacionPanoService {
    @Autowired
    private AceptacionPlanoRepo aceptacionPlanoRepo;

    @Autowired
    private DibujoPlanoRepo dibujoPlanoRepo;

    @Autowired
    private DibujoPlanoService dibujoPlanoService;

    // Crear una nueva aceptación de plano
    public AceptacionPlanoDTO crearAceptacionPlano(AceptacionPlanoDTO aceptacionPlanoDTO) {
        AceptacionPlano aceptacionPlano = mapToEntity(aceptacionPlanoDTO);
        AceptacionPlano nuevaAceptacionPlano = aceptacionPlanoRepo.save(aceptacionPlano);

        // Actualizar el estado del DibujoPlano
        DibujoPlanoDTO dibujoPlanoDTO = dibujoPlanoService.obtenerDibujoPlanoPorId(aceptacionPlanoDTO.getDibujoPlanoId());
        dibujoPlanoDTO.setEstado(aceptacionPlanoDTO.getEstado());
        dibujoPlanoService.actualizarDibujoPlano(dibujoPlanoDTO);

        return mapToDTO(nuevaAceptacionPlano);
    }

    // Obtener todas las aceptaciones de plano
    public List<AceptacionPlanoDTO> obtenerTodasLasAceptaciones() {
        List<AceptacionPlano> aceptacionesPlano = aceptacionPlanoRepo.findAll();
        return aceptacionesPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

    }

    // Obtener una aceptación de plano por su ID
    public AceptacionPlanoDTO obtenerAceptacionPorId(Long id) {
        AceptacionPlano aceptacionPlano = aceptacionPlanoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AceptacionPlano no encontrada con ID: " + id));
        return mapToDTO(aceptacionPlano);
    }

    // Actualizar una aceptación de plano existente
    public AceptacionPlanoDTO actualizarAceptacionPlano(AceptacionPlanoDTO aceptacionPlanoDTO) {
        AceptacionPlano aceptacionPlano = mapToEntity(aceptacionPlanoDTO);
        AceptacionPlano aceptacionPlanoActualizado = aceptacionPlanoRepo.save(aceptacionPlano);

        // Actualizar el estado del DibujoPlano
        DibujoPlanoDTO dibujoPlanoDTO = dibujoPlanoService.obtenerDibujoPlanoPorId(aceptacionPlanoDTO.getDibujoPlanoId());
        dibujoPlanoDTO.setEstado(aceptacionPlanoDTO.getEstado());
        dibujoPlanoService.actualizarDibujoPlano(dibujoPlanoDTO);

        return mapToDTO(aceptacionPlanoActualizado);
    }

    // Eliminar una aceptación de plano por su ID
    public void eliminarAceptacionPlano(Long id) {
        aceptacionPlanoRepo.deleteById(id);
    }
    private AceptacionPlanoDTO mapToDTO(AceptacionPlano aceptacionPlano) {
        AceptacionPlanoDTO dto = new AceptacionPlanoDTO();
        dto.setId(aceptacionPlano.getId());
        dto.setAcceptanceDate(aceptacionPlano.getAcceptanceDate());
        dto.setComments(aceptacionPlano.getComments());
        dto.setEstado(aceptacionPlano.getEstado());

        DibujoPlano dibujoPlano = aceptacionPlano.getDibujoPlano();
        if (dibujoPlano != null) {
            dto.setDibujoPlanoId(dibujoPlano.getId());
        }

        return dto;
    }

    private AceptacionPlano mapToEntity(AceptacionPlanoDTO dto) {
        AceptacionPlano aceptacionPlano = new AceptacionPlano();
        aceptacionPlano.setId(dto.getId());
        aceptacionPlano.setAcceptanceDate(dto.getAcceptanceDate());
        aceptacionPlano.setComments(dto.getComments());
        aceptacionPlano.setEstado(dto.getEstado());

        Long dibujoPlanoId = dto.getDibujoPlanoId();
        if (dibujoPlanoId != null) {
            DibujoPlano dibujoPlano = dibujoPlanoRepo.findById(dibujoPlanoId)
                    .orElseThrow(() -> new EntityNotFoundException("DibujoPlano no encontrado con ID: " + dibujoPlanoId));
            aceptacionPlano.setDibujoPlano(dibujoPlano);
        }

        return aceptacionPlano;
    }



}
