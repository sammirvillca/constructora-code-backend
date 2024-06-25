package com.server.constructionapp.service;

import com.server.constructionapp.model.Cliente;
import com.server.constructionapp.repository.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo clienteRepo;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepo.findById(id);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public void eliminarClientePorId(Long id) {
        clienteRepo.deleteById(id);
    }

    public void eliminarCliente(Cliente cliente) {
        clienteRepo.delete(cliente);
    }
}
