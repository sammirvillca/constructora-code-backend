package com.server.constructionapp.service;

import com.server.constructionapp.DTO.EntregaDTO;
import com.server.constructionapp.DTO.ProyectoDTO;
import com.server.constructionapp.model.*;
import com.server.constructionapp.repository.ClienteRepo;
import com.server.constructionapp.repository.EntregaRepo;
import com.server.constructionapp.repository.ProyectoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepo entregaRepo;
    @Autowired
    private ProyectoRepo proyectoRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    public EntregaDTO crearEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = new Entrega();
        entrega.setCodEntrega(entregaDTO.getCodEntrega());
        entrega.setDeadline(entregaDTO.getDeadline());

        Proyecto proyecto = proyectoRepo.findById(entregaDTO.getProyectoId())
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + entregaDTO.getProyectoId()));
        entrega.setProyecto(proyecto);

        Cliente cliente = Optional.ofNullable(proyecto.getDibujoPlano())
                .map(DibujoPlano::getPrerequisitoPlano)
                .map(PrerequisitoPlano::getCliente)
                .orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar el cliente asociado al proyecto"));
        entrega.setCliente(cliente);

        Entrega nuevaEntrega = entregaRepo.save(entrega);
        return mapToDTO(nuevaEntrega);
    }

    public List<ProyectoDTO> obtenerProyectosSinEntrega() {
        return proyectoRepo.findProyectosSinEntrega().stream()
                .map(this::mapProyectoToDTO)
                .collect(Collectors.toList());
    }

    public EntregaDTO obtenerEntrega(Long id) {
        Entrega entrega = entregaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrega no encontrada con ID: " + id));
        return mapToDTO(entrega);
    }

    public List<EntregaDTO> obtenerTodasLasEntregas() {
        List<Entrega> entregas = entregaRepo.findAll();
        return entregas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EntregaDTO actualizarEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = mapToEntity(entregaDTO);
        Entrega entregaActualizada = entregaRepo.save(entrega);
        return mapToDTO(entregaActualizada);
    }

    public void eliminarEntrega(Long id) {
        entregaRepo.deleteById(id);
    }

    private EntregaDTO mapToDTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setId(entrega.getId());
        dto.setDeadline(entrega.getDeadline());
        dto.setCodEntrega(entrega.getCodEntrega());

        Proyecto proyecto = entrega.getProyecto();
        if (proyecto != null) {
            dto.setProyectoId(proyecto.getId());
        }

        Cliente cliente = entrega.getCliente();
        if (cliente != null) {
            dto.setClienteId(cliente.getId());
        }

        return dto;
    }

    private Entrega mapToEntity(EntregaDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setId(dto.getId());
        entrega.setDeadline(dto.getDeadline());
        entrega.setCodEntrega(dto.getCodEntrega());

        Long proyectoId = dto.getProyectoId();
        if (proyectoId != null) {
            Proyecto proyecto = proyectoRepo.findById(proyectoId)
                    .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));
            entrega.setProyecto(proyecto);
        }

        Long clienteId = dto.getClienteId();
        if (clienteId != null) {
            Cliente cliente = clienteRepo.findById(clienteId)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));
            entrega.setCliente(cliente);
        }

        return entrega;
    }

    private ProyectoDTO mapProyectoToDTO(Proyecto proyecto) {
        ProyectoDTO dto = new ProyectoDTO();
        dto.setId(proyecto.getId());
        dto.setCodProyecto(proyecto.getCodProyecto());
        // Mapear otros campos necesarios
        DibujoPlano dibujoPlano = proyecto.getDibujoPlano();
        if (dibujoPlano != null && dibujoPlano.getPrerequisitoPlano() != null) {
            Cliente cliente = dibujoPlano.getPrerequisitoPlano().getCliente();
            if (cliente != null) {
                dto.setClienteId(cliente.getId());
                dto.setClienteCodCliente(cliente.getCodCliente());
                dto.setClienteFullName(cliente.getFullName());
            }
        }
        return dto;
    }
}
