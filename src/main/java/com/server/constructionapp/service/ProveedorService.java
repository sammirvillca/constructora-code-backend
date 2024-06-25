package com.server.constructionapp.service;

import com.server.constructionapp.DTO.CatalogoProveedorDTO;
import com.server.constructionapp.DTO.ProveedorDTO;
import com.server.constructionapp.model.CatalogoProveedor;
import com.server.constructionapp.model.Proveedor;
import com.server.constructionapp.repository.ProveedorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepo proveedorRepo;

    public ProveedorDTO crearProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = mapToEntity(proveedorDTO);
        Proveedor nuevoProveedor = proveedorRepo.save(proveedor);
        return mapToDTO(nuevoProveedor);
    }

    public ProveedorDTO obtenerProveedor(Long id) {
        Proveedor proveedor = proveedorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        return mapToDTO(proveedor);
    }

    public List<ProveedorDTO> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = proveedorRepo.findAll();
        return proveedores.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProveedorDTO actualizarProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = mapToEntity(proveedorDTO);
        Proveedor proveedorActualizado = proveedorRepo.save(proveedor);
        return mapToDTO(proveedorActualizado);
    }

    public void eliminarProveedor(Long id) {
        proveedorRepo.deleteById(id);
    }

    public List<CatalogoProveedorDTO> obtenerCatalogosDeProveedor(Long proveedorId) {
        Proveedor proveedor = proveedorRepo.findById(proveedorId)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorId));

        List<CatalogoProveedor> catalogos = proveedor.getCatalogo();
        return catalogos.stream()
                .map(this::mapToCatalogoProveedorDTO)
                .collect(Collectors.toList());
    }

    private CatalogoProveedorDTO mapToCatalogoProveedorDTO(CatalogoProveedor catalogoProveedor) {
        CatalogoProveedorDTO dto = new CatalogoProveedorDTO();
        dto.setId(catalogoProveedor.getId());
        dto.setMaterial(catalogoProveedor.getMaterial());
        // Mapea otros campos relevantes del catálogo de proveedor
        return dto;
    }

    private ProveedorDTO mapToDTO(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setName(proveedor.getName());
        dto.setAddress(proveedor.getAddress());
        dto.setCity(proveedor.getCity());
        dto.setCountry(proveedor.getCountry());
        dto.setPhone(proveedor.getPhone());
        dto.setEmail(proveedor.getEmail());
        dto.setCodProveedor(proveedor.getCodProveedor());
        return dto;
    }

    private Proveedor mapToEntity(ProveedorDTO dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(dto.getId());
        proveedor.setName(dto.getName());
        proveedor.setAddress(dto.getAddress());
        proveedor.setCity(dto.getCity());
        proveedor.setCountry(dto.getCountry());
        proveedor.setPhone(dto.getPhone());
        proveedor.setEmail(dto.getEmail());
        proveedor.setCodProveedor(dto.getCodProveedor());
        return proveedor;
    }
}
