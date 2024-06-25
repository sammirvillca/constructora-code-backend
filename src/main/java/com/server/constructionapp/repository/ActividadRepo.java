package com.server.constructionapp.repository;

import com.server.constructionapp.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepo extends JpaRepository<Actividad, Long> {
    List<Actividad> findByCronogramaId(Long cronogramaId);

}
