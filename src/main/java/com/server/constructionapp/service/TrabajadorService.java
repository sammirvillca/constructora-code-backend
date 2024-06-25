package com.server.constructionapp.service;

import com.server.constructionapp.DTO.TrabajadorDTO;
import com.server.constructionapp.model.Trabajador;
import com.server.constructionapp.repository.TrabajadorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepo trabajadorRepo;

    public TrabajadorDTO crearTrabajador(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = mapToEntity(trabajadorDTO);
        Trabajador nuevoTrabajador = trabajadorRepo.save(trabajador);
        return mapToDTO(nuevoTrabajador);
    }

    public TrabajadorDTO obtenerTrabajador(Long id) {
        Trabajador trabajador = trabajadorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado con ID: " + id));
        return mapToDTO(trabajador);
    }

    public List<TrabajadorDTO> obtenerTodosLosTrabajadores() {
        List<Trabajador> trabajadores = trabajadorRepo.findAll();
        return trabajadores.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TrabajadorDTO actualizarTrabajador(TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = mapToEntity(trabajadorDTO);
        Trabajador trabajadorActualizado = trabajadorRepo.save(trabajador);
        return mapToDTO(trabajadorActualizado);
    }

    public void eliminarTrabajador(Long id) {
        trabajadorRepo.deleteById(id);
    }

    private TrabajadorDTO mapToDTO(Trabajador trabajador) {
        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setId(trabajador.getId());
        dto.setFullName(trabajador.getFullName());
        dto.setIdentityCard(trabajador.getIdentityCard());
        dto.setRol(trabajador.getRol());
        dto.setAddress(trabajador.getAddress());
        dto.setEmail(trabajador.getEmail());
        dto.setPhone(trabajador.getPhone());
        dto.setCodTrabajador(trabajador.getCodTrabajador());
        return dto;
    }

    private Trabajador mapToEntity(TrabajadorDTO dto) {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(dto.getId());
        trabajador.setFullName(dto.getFullName());
        trabajador.setIdentityCard(dto.getIdentityCard());
        trabajador.setRol(dto.getRol());
        trabajador.setAddress(dto.getAddress());
        trabajador.setEmail(dto.getEmail());
        trabajador.setPhone(dto.getPhone());
        trabajador.setCodTrabajador(dto.getCodTrabajador());
        return trabajador;
    }
}
