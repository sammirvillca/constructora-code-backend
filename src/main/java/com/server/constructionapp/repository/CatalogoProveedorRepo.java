package com.server.constructionapp.repository;

import com.server.constructionapp.model.CatalogoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogoProveedorRepo extends JpaRepository<CatalogoProveedor, Long> {
    List<CatalogoProveedor> findByProveedorId(Long proveedorId);
}
