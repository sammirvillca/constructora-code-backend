package com.server.constructionapp.service;

import com.server.constructionapp.DTO.CatalogoProveedorDTO;
import com.server.constructionapp.model.CatalogoProveedor;
import com.server.constructionapp.model.Proveedor;
import com.server.constructionapp.repository.CatalogoProveedorRepo;
import com.server.constructionapp.repository.ProveedorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatalogoProveedorService {
    @Autowired
    private CatalogoProveedorRepo catalogoProveedorRepo;
    @Autowired
    private ProveedorRepo proveedorRepo;

    public CatalogoProveedorDTO crearCatalogoProveedor(CatalogoProveedorDTO catalogoProveedorDTO) {
        CatalogoProveedor catalogoProveedor = mapToEntity(catalogoProveedorDTO);
        CatalogoProveedor nuevoCatalogoProveedor = catalogoProveedorRepo.save(catalogoProveedor);
        return mapToDTO(nuevoCatalogoProveedor);
    }

    public CatalogoProveedorDTO obtenerCatalogoProveedor(Long id) {
        CatalogoProveedor catalogoProveedor = catalogoProveedorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CatalogoProveedor no encontrado con ID: " + id));
        return mapToDTO(catalogoProveedor);
    }

    public List<CatalogoProveedorDTO> obtenerTodosLosCatalogosProveedor() {
        List<CatalogoProveedor> catalogosProveedor = catalogoProveedorRepo.findAll();
        return catalogosProveedor.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CatalogoProveedorDTO actualizarCatalogoProveedor(CatalogoProveedorDTO catalogoProveedorDTO) {
        CatalogoProveedor catalogoProveedor = mapToEntity(catalogoProveedorDTO);
        CatalogoProveedor catalogoProveedorActualizado = catalogoProveedorRepo.save(catalogoProveedor);
        return mapToDTO(catalogoProveedorActualizado);
    }

    public void eliminarCatalogoProveedor(Long id) {
        catalogoProveedorRepo.deleteById(id);
    }

    public List<CatalogoProveedorDTO> obtenerCatalogosProveedorPorId(Long proveedorId) {
        List<CatalogoProveedor> catalogosProveedor = catalogoProveedorRepo.findByProveedorId(proveedorId);
        return catalogosProveedor.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CatalogoProveedorDTO mapToDTO(CatalogoProveedor catalogoProveedor) {
        CatalogoProveedorDTO dto = new CatalogoProveedorDTO();
        dto.setId(catalogoProveedor.getId());
        dto.setMaterial(catalogoProveedor.getMaterial());
        dto.setAmountMaterial(catalogoProveedor.getAmountMaterial());
        dto.setCost(catalogoProveedor.getCost());

        Proveedor proveedor = catalogoProveedor.getProveedor();
        if (proveedor != null) {
            dto.setProveedorId(proveedor.getId());
        }

        return dto;
    }

    private CatalogoProveedor mapToEntity(CatalogoProveedorDTO dto) {
        CatalogoProveedor catalogoProveedor = new CatalogoProveedor();
        catalogoProveedor.setId(dto.getId());
        catalogoProveedor.setMaterial(dto.getMaterial());
        catalogoProveedor.setAmountMaterial(dto.getAmountMaterial());
        catalogoProveedor.setCost(dto.getCost());

        Long proveedorId = dto.getProveedorId();
        if (proveedorId != null) {
            Proveedor proveedor = proveedorRepo.findById(proveedorId)
                    .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + proveedorId));
            catalogoProveedor.setProveedor(proveedor);
        }

        return catalogoProveedor;
    }
}
