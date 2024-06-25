package com.server.constructionapp.service;

import com.server.constructionapp.DTO.PrerequisitoPlanoDTO;
import com.server.constructionapp.model.Cliente;
import com.server.constructionapp.model.PrerequisitoPlano;
import com.server.constructionapp.model.Trabajador;
import com.server.constructionapp.repository.ClienteRepo;
import com.server.constructionapp.repository.PrerequisitoPlanoRepo;
import com.server.constructionapp.repository.TrabajadorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrerequisitoPlanoService {
    @Autowired
    private PrerequisitoPlanoRepo prerequisitoPlanoRepo;
    @Autowired
    private ClienteRepo clienteRepo;
    @Autowired
    private TrabajadorRepo trabajadorRepo;

    public PrerequisitoPlanoDTO crearPrerequisitoPlano(PrerequisitoPlanoDTO prerequisitoPlanoDTO) {
        PrerequisitoPlano prerequisitoPlano = mapToEntity(prerequisitoPlanoDTO);
        PrerequisitoPlano nuevoPrerequisitoPlano = prerequisitoPlanoRepo.save(prerequisitoPlano);
        return mapToDTO(nuevoPrerequisitoPlano);
    }

    public List<PrerequisitoPlanoDTO> obtenerTodosLosPrerequisitosPlano() {
        List<PrerequisitoPlano> prerequisitosPlano = prerequisitoPlanoRepo.findAll();
        return prerequisitosPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public PrerequisitoPlanoDTO obtenerPrerequisitoPlanoPorId(Long id) {
        PrerequisitoPlano prerequisitoPlano = prerequisitoPlanoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PrerequisitoPlano no encontrado con ID: " + id));
        return mapToDTO(prerequisitoPlano);
    }

    public PrerequisitoPlanoDTO actualizarPrerequisitoPlano(PrerequisitoPlanoDTO prerequisitoPlanoDTO) {
        PrerequisitoPlano prerequisitoPlano = mapToEntity(prerequisitoPlanoDTO);
        PrerequisitoPlano prerequisitoPlanoActualizado = prerequisitoPlanoRepo.save(prerequisitoPlano);
        return mapToDTO(prerequisitoPlanoActualizado);
    }

    public void eliminarPrerequisitoPlano(Long id) {
        prerequisitoPlanoRepo.deleteById(id);
    }

    public List<PrerequisitoPlanoDTO> obtenerPrerequisitosPlanoNoVinculados() {
        List<PrerequisitoPlano> prerequisitosPlano = prerequisitoPlanoRepo.findByDibujoPlanoIsNull();
        return prerequisitosPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<PrerequisitoPlanoDTO> obtenerPrerequisitosPlanosPorCliente(Long clienteId) {
        List<PrerequisitoPlano> prerequisitosPlano = prerequisitoPlanoRepo.findByCliente_Id(clienteId);
        return prerequisitosPlano.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PrerequisitoPlanoDTO mapToDTO(PrerequisitoPlano prerequisitoPlano) {
        PrerequisitoPlanoDTO dto = new PrerequisitoPlanoDTO();
        dto.setId(prerequisitoPlano.getId());
        dto.setRoomsQuantity(prerequisitoPlano.getRoomsQuantity());
        dto.setFloorsQuantity(prerequisitoPlano.getFloorsQuantity());
        dto.setBathroomsQuantity(prerequisitoPlano.getBathroomsQuantity());
        dto.setSize(prerequisitoPlano.getSize());
        dto.setTastes(prerequisitoPlano.getTastes());
        dto.setDislikes(prerequisitoPlano.getDislikes());
        dto.setCodReq(prerequisitoPlano.getCodReq());

        Cliente cliente = prerequisitoPlano.getCliente();
        if (cliente != null) {
            dto.setClienteId(cliente.getId());
            dto.setClienteFullName(cliente.getFullName());
        }

        Trabajador trabajador = prerequisitoPlano.getTrabajador();
        if (trabajador != null) {
            dto.setTrabajadorId(trabajador.getId());
        }
        return dto;
    }

    private PrerequisitoPlano mapToEntity(PrerequisitoPlanoDTO dto) {
        PrerequisitoPlano prerequisitoPlano = new PrerequisitoPlano();
        prerequisitoPlano.setId(dto.getId());
        prerequisitoPlano.setRoomsQuantity(dto.getRoomsQuantity());
        prerequisitoPlano.setFloorsQuantity(dto.getFloorsQuantity());
        prerequisitoPlano.setBathroomsQuantity(dto.getBathroomsQuantity());
        prerequisitoPlano.setSize(dto.getSize());
        prerequisitoPlano.setTastes(dto.getTastes());
        prerequisitoPlano.setDislikes(dto.getDislikes());
        prerequisitoPlano.setCodReq(dto.getCodReq());

        Long clienteId = dto.getClienteId();
        if (clienteId != null) {
            Cliente cliente = clienteRepo.findById(clienteId)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));
            prerequisitoPlano.setCliente(cliente);
        }

        Long trabajadorId = dto.getTrabajadorId();
        if (trabajadorId != null) {
            Trabajador trabajador = trabajadorRepo.findById(trabajadorId)
                    .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado con ID: " + trabajadorId));
            prerequisitoPlano.setTrabajador(trabajador);
        }

        return prerequisitoPlano;
    }


}
