package com.server.constructionapp.repository;

import com.server.constructionapp.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepo extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findById(Long id);
}
