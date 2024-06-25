package com.server.constructionapp.service;

import com.server.constructionapp.DTO.CronogramaDTO;
import com.server.constructionapp.model.Cronograma;
import com.server.constructionapp.repository.CronogramaRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CronogramaService {
    @Autowired
    private CronogramaRepo cronogramaRepo;

    public CronogramaDTO crearCronograma(CronogramaDTO cronogramaDTO) {
        Cronograma cronograma = mapToEntity(cronogramaDTO);
        cronograma.setCodCronograma(cronogramaDTO.getCodCronograma());
        Cronograma nuevoCronograma = cronogramaRepo.save(cronograma);
        return mapToDTO(nuevoCronograma);
    }

    public CronogramaDTO obtenerCronograma(Long id) {
        Cronograma cronograma = cronogramaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cronograma no encontrado con ID: " + id));
        return mapToDTO(cronograma);
    }

    public List<CronogramaDTO> obtenerTodosLosCronogramas() {
        List<Cronograma> cronogramas = cronogramaRepo.findAll();
        return cronogramas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CronogramaDTO actualizarCronograma(CronogramaDTO cronogramaDTO) {
        Cronograma cronograma = mapToEntity(cronogramaDTO);
        cronograma.setCodCronograma(cronogramaDTO.getCodCronograma());
        Cronograma cronogramaActualizado = cronogramaRepo.save(cronograma);
        return mapToDTO(cronogramaActualizado);
    }

    public void eliminarCronograma(Long id) {
        cronogramaRepo.deleteById(id);
    }

    private CronogramaDTO mapToDTO(Cronograma cronograma) {
        CronogramaDTO dto = new CronogramaDTO();
        dto.setId(cronograma.getId());
        dto.setStartDate(cronograma.getStartDate());
        dto.setEndDate(cronograma.getEndDate());
        dto.setCodCronograma(cronograma.getCodCronograma());
        return dto;
    }

    private Cronograma mapToEntity(CronogramaDTO dto) {
        Cronograma cronograma = new Cronograma();
        cronograma.setId(dto.getId());
        cronograma.setStartDate(dto.getStartDate());
        cronograma.setEndDate(dto.getEndDate());
        return cronograma;
    }

}
