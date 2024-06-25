package com.server.constructionapp.repository;

import com.server.constructionapp.model.DepositoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositoClienteRepo extends JpaRepository<DepositoCliente, Long> {
    List<DepositoCliente> findByDatosCliente_Id(Long datosClienteId);
}
