package com.server.constructionapp.service;

import com.server.constructionapp.model.LibroConstruccion;
import com.server.constructionapp.repository.LibroConstruccionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroConstruccionService {
    @Autowired
    private LibroConstruccionRepo libroConstruccionRepo;

    public LibroConstruccion crearLibroConstruccion(LibroConstruccion libroConstruccion) {
        return libroConstruccionRepo.save(libroConstruccion);
    }

    public List<LibroConstruccion> obtenerTodosLosLibrosConstruccion() {
        return libroConstruccionRepo.findAll();
    }

    public Optional<LibroConstruccion> obtenerLibroConstruccionPorId(Long id) {
        return libroConstruccionRepo.findById(id);
    }

    public LibroConstruccion actualizarLibroConstruccion(LibroConstruccion libroConstruccion) {
        return libroConstruccionRepo.save(libroConstruccion);
    }

    public void eliminarLibroConstruccion(Long id) {
        libroConstruccionRepo.deleteById(id);
    }
}
