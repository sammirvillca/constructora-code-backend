package com.server.constructionapp.repository;

import com.server.constructionapp.model.ContratoConstruccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoConstruccionRepo extends JpaRepository<ContratoConstruccion, Long> {
}
