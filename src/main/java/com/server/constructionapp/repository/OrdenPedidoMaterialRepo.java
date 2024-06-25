package com.server.constructionapp.repository;

import com.server.constructionapp.model.OrdenPedidoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenPedidoMaterialRepo extends JpaRepository<OrdenPedidoMaterial, Long> {
    List<OrdenPedidoMaterial> findByProyectoId(Long proyectoId);

}
