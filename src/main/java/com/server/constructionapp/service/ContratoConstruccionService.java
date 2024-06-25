package com.server.constructionapp.service;

import com.server.constructionapp.DTO.ContratoConstruccionDTO;
import com.server.constructionapp.model.ContratoConstruccion;
import com.server.constructionapp.model.Proyecto;
import com.server.constructionapp.repository.ContratoConstruccionRepo;
import com.server.constructionapp.repository.ProyectoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratoConstruccionService {
    @Autowired
    private ContratoConstruccionRepo contratoConstruccionRepo;
    @Autowired
    private ProyectoRepo proyectoRepo;

    public ContratoConstruccionDTO crearContratoConstruccion(ContratoConstruccionDTO contratoConstruccionDTO) {
        ContratoConstruccion contratoConstruccion = mapToEntity(contratoConstruccionDTO);
        ContratoConstruccion nuevoContratoConstruccion = contratoConstruccionRepo.save(contratoConstruccion);
        return mapToDTO(nuevoContratoConstruccion);
    }

    public ContratoConstruccionDTO obtenerContratoConstruccion(Long id) {
        ContratoConstruccion contratoConstruccion = contratoConstruccionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ContratoConstruccion no encontrado con ID: " + id));
        return mapToDTO(contratoConstruccion);
    }

    public List<ContratoConstruccionDTO> obtenerTodosLosContratosConstruccion() {
        List<ContratoConstruccion> contratosConstruccion = contratoConstruccionRepo.findAll();
        return contratosConstruccion.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContratoConstruccionDTO actualizarContratoConstruccion(ContratoConstruccionDTO contratoConstruccionDTO) {
        ContratoConstruccion contratoConstruccion = mapToEntity(contratoConstruccionDTO);
        ContratoConstruccion contratoConstruccionActualizado = contratoConstruccionRepo.save(contratoConstruccion);
        return mapToDTO(contratoConstruccionActualizado);
    }

    public void eliminarContratoConstruccion(Long id) {
        contratoConstruccionRepo.deleteById(id);
    }

    private ContratoConstruccionDTO mapToDTO(ContratoConstruccion contratoConstruccion) {
        ContratoConstruccionDTO dto = new ContratoConstruccionDTO();
        dto.setId(contratoConstruccion.getId());
        dto.setConstructionDoc(contratoConstruccion.getConstructionDoc());
        dto.setCodContConstruccion(contratoConstruccion.getCodContConstruccion());

        Proyecto proyecto = contratoConstruccion.getProyecto();
        if (proyecto != null) {
            dto.setProyectoId(proyecto.getId());
        }

        return dto;
    }

    private ContratoConstruccion mapToEntity(ContratoConstruccionDTO dto) {
        ContratoConstruccion contratoConstruccion = new ContratoConstruccion();
        contratoConstruccion.setId(dto.getId());
        contratoConstruccion.setConstructionDoc(dto.getConstructionDoc());
        contratoConstruccion.setCodContConstruccion(dto.getCodContConstruccion());

        Long proyectoId = dto.getProyectoId();
        if (proyectoId != null) {
            Proyecto proyecto = proyectoRepo.findById(proyectoId)
                    .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));
            contratoConstruccion.setProyecto(proyecto);
        }

        return contratoConstruccion;
    }

}
