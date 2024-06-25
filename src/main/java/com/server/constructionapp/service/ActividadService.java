package com.server.constructionapp.service;

import com.server.constructionapp.DTO.ActividadDTO;
import com.server.constructionapp.model.Actividad;
import com.server.constructionapp.model.Cronograma;
import com.server.constructionapp.repository.ActividadRepo;
import com.server.constructionapp.repository.CronogramaRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepo actividadRepo;
    @Autowired
    private CronogramaRepo cronogramaRepo;

    public ActividadDTO crearActividad(Long cronogramaId, ActividadDTO actividadDTO) {
        Cronograma cronograma = cronogramaRepo.findById(cronogramaId)
                .orElseThrow(() -> new EntityNotFoundException("Cronograma no encontrado con ID: " + cronogramaId));

        Actividad actividad = mapToEntity(actividadDTO);
        actividad.setCronograma(cronograma);
        Actividad nuevaActividad = actividadRepo.save(actividad);
        return mapToDTO(nuevaActividad);
    }

    public ActividadDTO obtenerActividad(Long id) {
        Actividad actividad = actividadRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada con ID: " + id));
        return mapToDTO(actividad);
    }

    public List<ActividadDTO> obtenerActividadesPorCronogramaId(Long cronogramaId) {
        List<Actividad> actividades = actividadRepo.findByCronogramaId(cronogramaId);
        return actividades.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ActividadDTO actualizarActividad(Long id, ActividadDTO actividadDTO) {
        Actividad actividad = actividadRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada con ID: " + id));

        actividad.setName(actividadDTO.getName());
        actividad.setDescription(actividadDTO.getDescription());
        actividad.setStartDate(actividadDTO.getStartDate());
        actividad.setEndDate(actividadDTO.getEndDate());

        Actividad actividadActualizada = actividadRepo.save(actividad);
        return mapToDTO(actividadActualizada);
    }

    public void eliminarActividad(Long id) {
        actividadRepo.deleteById(id);
    }

    private ActividadDTO mapToDTO(Actividad actividad) {
        ActividadDTO dto = new ActividadDTO();
        dto.setId(actividad.getId());
        dto.setName(actividad.getName());
        dto.setDescription(actividad.getDescription());
        dto.setStartDate(actividad.getStartDate());
        dto.setEndDate(actividad.getEndDate());
        return dto;
    }

    private Actividad mapToEntity(ActividadDTO dto) {
        Actividad actividad = new Actividad();
        actividad.setId(dto.getId());
        actividad.setName(dto.getName());
        actividad.setDescription(dto.getDescription());
        actividad.setStartDate(dto.getStartDate());
        actividad.setEndDate(dto.getEndDate());
        return actividad;
    }
}
