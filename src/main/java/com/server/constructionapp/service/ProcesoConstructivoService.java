package com.server.constructionapp.service;

import com.server.constructionapp.DTO.ProcesoConstructivoDTO;
import com.server.constructionapp.model.Cronograma;
import com.server.constructionapp.model.ProcesoConstructivo;
import com.server.constructionapp.model.Trabajador;
import com.server.constructionapp.repository.CronogramaRepo;
import com.server.constructionapp.repository.ProcesoConstructivoRepo;
import com.server.constructionapp.repository.TrabajadorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcesoConstructivoService {
    @Autowired
    private ProcesoConstructivoRepo procesoConstructivoRepo;
    @Autowired
    private CronogramaRepo cronogramaRepo;
    @Autowired
    private TrabajadorRepo trabajadorRepo;

    public ProcesoConstructivoDTO crearProcesoConstructivo(ProcesoConstructivoDTO procesoConstructivoDTO) {
        ProcesoConstructivo procesoConstructivo = mapToEntity(procesoConstructivoDTO);
        ProcesoConstructivo nuevoProcesoConstructivo = procesoConstructivoRepo.save(procesoConstructivo);
        return mapToDTO(nuevoProcesoConstructivo);
    }

    public ProcesoConstructivoDTO obtenerProcesoConstructivo(Long id) {
        ProcesoConstructivo procesoConstructivo = procesoConstructivoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ProcesoConstructivo no encontrado con ID: " + id));
        return mapToDTO(procesoConstructivo);
    }

    public List<ProcesoConstructivoDTO> obtenerTodosLosProcesosConstructivos() {
        List<ProcesoConstructivo> procesosConstructivos = procesoConstructivoRepo.findAll();
        return procesosConstructivos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProcesoConstructivoDTO actualizarProcesoConstructivo(ProcesoConstructivoDTO procesoConstructivoDTO) {
        ProcesoConstructivo procesoConstructivo = mapToEntity(procesoConstructivoDTO);
        ProcesoConstructivo procesoConstructivoActualizado = procesoConstructivoRepo.save(procesoConstructivo);
        return mapToDTO(procesoConstructivoActualizado);
    }

    public void eliminarProcesoConstructivo(Long id) {
        procesoConstructivoRepo.deleteById(id);
    }

    private ProcesoConstructivoDTO mapToDTO(ProcesoConstructivo procesoConstructivo) {
        ProcesoConstructivoDTO dto = new ProcesoConstructivoDTO();
        dto.setId(procesoConstructivo.getId());
        dto.setRevision(procesoConstructivo.getRevision());
        dto.setPointOfView(procesoConstructivo.getPointOfView());
        dto.setImprovements(procesoConstructivo.getImprovements());
        dto.setMeetsTheSchedule(procesoConstructivo.getMeetsTheSchedule());

        Cronograma cronograma = procesoConstructivo.getCronograma();
        if (cronograma != null) {
            dto.setCronogramaId(cronograma.getId());
        }

        Trabajador encargado = procesoConstructivo.getEncargado();
        if (encargado != null) {
            dto.setEncargadoId(encargado.getId());
        }

        return dto;
    }

    private ProcesoConstructivo mapToEntity(ProcesoConstructivoDTO dto) {
        ProcesoConstructivo procesoConstructivo = new ProcesoConstructivo();
        procesoConstructivo.setId(dto.getId());
        procesoConstructivo.setRevision(dto.getRevision());
        procesoConstructivo.setPointOfView(dto.getPointOfView());
        procesoConstructivo.setImprovements(dto.getImprovements());
        procesoConstructivo.setMeetsTheSchedule(dto.getMeetsTheSchedule());

        Long cronogramaId = dto.getCronogramaId();
        if (cronogramaId != null) {
            Cronograma cronograma = cronogramaRepo.findById(cronogramaId)
                    .orElseThrow(() -> new EntityNotFoundException("Cronograma no encontrado con ID: " + cronogramaId));
            procesoConstructivo.setCronograma(cronograma);
        }

        Long encargadoId = dto.getEncargadoId();
        if (encargadoId != null) {
            Trabajador encargado = trabajadorRepo.findById(encargadoId)
                    .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado con ID: " + encargadoId));
            procesoConstructivo.setEncargado(encargado);
        }

        return procesoConstructivo;
    }


}
