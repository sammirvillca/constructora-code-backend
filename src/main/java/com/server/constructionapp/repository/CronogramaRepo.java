package com.server.constructionapp.repository;

import com.server.constructionapp.model.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CronogramaRepo extends JpaRepository<Cronograma, Long> {
    List<Cronograma> findByProyectoIsNull();

}
