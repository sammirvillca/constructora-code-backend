package com.server.constructionapp.service;

import com.server.constructionapp.DTO.OrdenPedidoMaterialDTO;
import com.server.constructionapp.model.CatalogoProveedor;
import com.server.constructionapp.model.OrdenPedidoMaterial;
import com.server.constructionapp.model.Proveedor;
import com.server.constructionapp.model.Proyecto;
import com.server.constructionapp.repository.CatalogoProveedorRepo;
import com.server.constructionapp.repository.OrdenPedidoMaterialRepo;
import com.server.constructionapp.repository.ProveedorRepo;
import com.server.constructionapp.repository.ProyectoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenPedidoMaterialService {
    @Autowired
    private OrdenPedidoMaterialRepo ordenPedidoMaterialRepo;
    @Autowired
    private ProyectoRepo proyectoRepo;
    @Autowired
    private CatalogoProveedorRepo catalogoProveedorRepo;

    public OrdenPedidoMaterialDTO crearOrdenPedidoMaterial(OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        OrdenPedidoMaterial ordenPedidoMaterial = mapToEntity(ordenPedidoMaterialDTO);
        OrdenPedidoMaterial nuevaOrdenPedidoMaterial = ordenPedidoMaterialRepo.save(ordenPedidoMaterial);
        return mapToDTO(nuevaOrdenPedidoMaterial);
    }

    public OrdenPedidoMaterialDTO obtenerOrdenPedidoMaterial(Long id) {
        OrdenPedidoMaterial ordenPedidoMaterial = ordenPedidoMaterialRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrdenPedidoMaterial no encontrada con ID: " + id));
        return mapToDTO(ordenPedidoMaterial);
    }

    public List<OrdenPedidoMaterialDTO> obtenerTodasLasOrdenesPedidoMaterial() {
        List<OrdenPedidoMaterial> ordenesPedidoMaterial = ordenPedidoMaterialRepo.findAll();
        return ordenesPedidoMaterial.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrdenPedidoMaterialDTO actualizarOrdenPedidoMaterial(OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        OrdenPedidoMaterial ordenPedidoMaterial = mapToEntity(ordenPedidoMaterialDTO);
        OrdenPedidoMaterial ordenPedidoMaterialActualizada = ordenPedidoMaterialRepo.save(ordenPedidoMaterial);
        return mapToDTO(ordenPedidoMaterialActualizada);
    }

    public void eliminarOrdenPedidoMaterial(Long id) {
        ordenPedidoMaterialRepo.deleteById(id);
    }

    private OrdenPedidoMaterialDTO mapToDTO(OrdenPedidoMaterial ordenPedidoMaterial) {
        OrdenPedidoMaterialDTO dto = new OrdenPedidoMaterialDTO();
        dto.setId(ordenPedidoMaterial.getId());
        dto.setOrderDate(ordenPedidoMaterial.getOrderDate());
        dto.setAmountMaterial(ordenPedidoMaterial.getAmountMaterial());

        Proyecto proyecto = ordenPedidoMaterial.getProyecto();
        if (proyecto != null) {
            dto.setProyectoId(proyecto.getId());
        }

        CatalogoProveedor catalogoProveedor = ordenPedidoMaterial.getCatalogoProveedor();
        if (catalogoProveedor != null) {
            dto.setCatalogoProveedorId(catalogoProveedor.getId());
        }

        return dto;
    }

    private OrdenPedidoMaterial mapToEntity(OrdenPedidoMaterialDTO dto) {
        OrdenPedidoMaterial ordenPedidoMaterial = new OrdenPedidoMaterial();
        ordenPedidoMaterial.setId(dto.getId());
        ordenPedidoMaterial.setOrderDate(dto.getOrderDate());
        ordenPedidoMaterial.setAmountMaterial(dto.getAmountMaterial());

        Long proyectoId = dto.getProyectoId();
        if (proyectoId != null) {
            Proyecto proyecto = proyectoRepo.findById(proyectoId)
                    .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado con ID: " + proyectoId));
            ordenPedidoMaterial.setProyecto(proyecto);
        }
        Long catalogoProveedorId = dto.getCatalogoProveedorId();
        if (catalogoProveedorId != null) {
            CatalogoProveedor catalogoProveedor = catalogoProveedorRepo.findById(catalogoProveedorId)
                    .orElseThrow(() -> new EntityNotFoundException("CatalogoProveedor no encontrado con ID: " + catalogoProveedorId));
            ordenPedidoMaterial.setCatalogoProveedor(catalogoProveedor);
        }

        return ordenPedidoMaterial;
    }
}
