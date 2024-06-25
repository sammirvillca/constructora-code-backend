package com.server.constructionapp.repository;

import com.server.constructionapp.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrabajadorRepo extends JpaRepository<Trabajador, Long> {
    List<Trabajador> findByRol(String rol);

}
