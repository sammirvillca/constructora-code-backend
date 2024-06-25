package com.server.constructionapp.repository;

import com.server.constructionapp.model.Cronograma;
import com.server.constructionapp.model.DibujoPlano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DibujoPlanoRepo extends JpaRepository<DibujoPlano, Long> {
    List<DibujoPlano> findByContratoPlanoIsNull();
    List<DibujoPlano> findByProyectoIsNull();

}
