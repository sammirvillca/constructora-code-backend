package com.server.constructionapp.repository;

import com.server.constructionapp.model.ProcesoConstructivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesoConstructivoRepo extends JpaRepository<ProcesoConstructivo, Long> {
}
