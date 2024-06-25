package com.server.constructionapp.repository;

import com.server.constructionapp.model.PrerequisitoPlano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisitoPlanoRepo extends JpaRepository<PrerequisitoPlano, Long> {
    List<PrerequisitoPlano> findByCliente_Id(Long clienteId);
    List<PrerequisitoPlano> findByDibujoPlanoIsNull();
}
