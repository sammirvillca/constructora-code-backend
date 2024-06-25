package com.server.constructionapp.repository;

import com.server.constructionapp.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepo extends JpaRepository<Proyecto, Long> {
    @Query("SELECT p FROM Proyecto p WHERE p.id NOT IN (SELECT cc.proyecto.id FROM ContratoConstruccion cc)")
    List<Proyecto> findProyectosNoVinculados();

    @Query("SELECT p FROM Proyecto p " +
            "WHERE p.id NOT IN (SELECT e.proyecto.id FROM Entrega e) " +
            "AND p.dibujoPlano IS NOT NULL " +
            "AND p.dibujoPlano.prerequisitoPlano IS NOT NULL " +
            "AND p.dibujoPlano.prerequisitoPlano.cliente IS NOT NULL")
    List<Proyecto> findProyectosSinEntrega();
}
