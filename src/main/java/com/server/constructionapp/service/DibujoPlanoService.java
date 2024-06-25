package com.server.constructionapp.service;

import com.server.constructionapp.DTO.DatosClienteDTO;
import com.server.constructionapp.DTO.DibujoPlanoDTO;
import com.server.constructionapp.model.DatosCliente;
import com.server.constructionapp.model.DibujoPlano;
import com.server.constructionapp.model.PrerequisitoPlano;
import com.server.constructionapp.repository.DibujoPlanoRepo;
import com.server.constructionapp.repository.PrerequisitoPlanoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DibujoPlanoService {
    @Autowired
    private DibujoPlanoRepo dibujoPlanoRepo;
    @Autowired
    private PrerequisitoPlanoRepo prerequisitoPlanoRepo;

    public DibujoPlanoDTO crearDibujoPlano(DibujoPlanoDTO dibujoPlanoDTO) {
        DibujoPlano dibujoPlano = mapToEntity(dibujoPlanoDTO);
        DibujoPlano nuevoDibujoPlano = dibujoPlanoRepo.save(dibujoPlano);
        return mapToDTO(nuevoDibujoPlano);
    }

    public DibujoPlanoDTO actualizarDibujoPlano(DibujoPlanoDTO dibujoPlanoDTO) {
        DibujoPlano dibujoPlanoExistente = dibujoPlanoRepo.findById(dibujoPlanoDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("DibujoPlano no encontrado con ID: " + dibujoPlanoDTO.getId()));

        dibujoPlanoExistente.setAtmosphere(dibujoPlanoDTO.getAtmosphere());
        dibujoPlanoExistente.setCirculationFlow(dibujoPlanoDTO.getCirculationFlow());
        dibujoPlanoExistente.setFuncionality(dibujoPlanoDTO.getFuncionality());
        dibujoPlanoExistente.setDimensions(dibujoPlanoDTO.getDimensions());
        dibujoPlanoExistente.setCodDiseño(dibujoPlanoDTO.getCodDiseño());
        dibujoPlanoExistente.setEstado(dibujoPlanoDTO.getEstado());

        if (dibujoPlanoDTO.getPrerequisitoPlanoId() != null) {
            PrerequisitoPlano prerequisitoPlano = prerequisitoPlanoRepo.findById(dibujoPlanoDTO.getPrerequisitoPlanoId())
                    .orElseThrow(() -> new EntityNotFoundException("PrerequisitoPlano no encontrado con ID: " + dibujoPlanoDTO.getPrerequisitoPlanoId()));
            dibujoPlanoExistente.setPrerequisitoPlano(prerequisitoPlano);
        }

        DibujoPlano dibujoPlanoActualizado = dibujoPlanoRepo.save(dibujoPlanoExistente);
        return mapToDTO(dibujoPlanoActualizado);
    }

    public DibujoPlanoDTO obtenerDibujoPlanoPorId(Long id) {
        DibujoPlano dibujoPlano = dibujoPlanoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DibujoPlano no encontrado con ID: " + id));
        return mapToDTO(dibujoPlano);
    }

    public List<DibujoPlanoDTO> obtenerTodosLosDibujosPlano() {
        List<DibujoPlano> dibujosPlano = dibujoPlanoRepo.findAll();
        return dibujosPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DibujoPlanoDTO> obtenerDibujoPlanoNoVinculados() {
        List<DibujoPlano> dibujoPlanos = dibujoPlanoRepo.findByContratoPlanoIsNull();
        return dibujoPlanos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void eliminarDibujoPlano(Long id) {
        dibujoPlanoRepo.deleteById(id);
    }

    private DibujoPlanoDTO mapToDTO(DibujoPlano dibujoPlano) {
        DibujoPlanoDTO dto = new DibujoPlanoDTO();
        dto.setId(dibujoPlano.getId());
        dto.setAtmosphere(dibujoPlano.getAtmosphere());
        dto.setCirculationFlow(dibujoPlano.getCirculationFlow());
        dto.setFuncionality(dibujoPlano.getFuncionality());
        dto.setDimensions(dibujoPlano.getDimensions());
        dto.setCodDiseño(dibujoPlano.getCodDiseño()); // Añade esta línea
        dto.setEstado(dibujoPlano.getEstado());

        PrerequisitoPlano prerequisitoPlano = dibujoPlano.getPrerequisitoPlano();
        if (prerequisitoPlano != null) {
            dto.setPrerequisitoPlanoId(prerequisitoPlano.getId());
        }

        return dto;
    }

    private DibujoPlano mapToEntity(DibujoPlanoDTO dto) {
        DibujoPlano dibujoPlano = new DibujoPlano();
        dibujoPlano.setId(dto.getId());
        dibujoPlano.setAtmosphere(dto.getAtmosphere());
        dibujoPlano.setCirculationFlow(dto.getCirculationFlow());
        dibujoPlano.setFuncionality(dto.getFuncionality());
        dibujoPlano.setDimensions(dto.getDimensions());
        dibujoPlano.setCodDiseño(dto.getCodDiseño());
        dibujoPlano.setEstado(dto.getEstado());

        Long prerequisitoPlanoId = dto.getPrerequisitoPlanoId();
        if (prerequisitoPlanoId != null) {
            PrerequisitoPlano prerequisitoPlano = prerequisitoPlanoRepo.findById(prerequisitoPlanoId)
                    .orElseThrow(() -> new EntityNotFoundException("PrerequisitoPlano no encontrado con ID: " + prerequisitoPlanoId));
            dibujoPlano.setPrerequisitoPlano(prerequisitoPlano);
        }

        return dibujoPlano;
    }
}
