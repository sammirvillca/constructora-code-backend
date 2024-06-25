package com.server.constructionapp.repository;

import com.server.constructionapp.model.DatosCliente;
import com.server.constructionapp.model.PrerequisitoPlano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatosClienteRepo extends JpaRepository<DatosCliente, Long> {
    List<DatosCliente> findByClienteId(Long clienteId);
    List<DatosCliente> findByContratoPlanoIsNull();

}
