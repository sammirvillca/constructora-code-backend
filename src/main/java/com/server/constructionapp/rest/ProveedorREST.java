package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.CatalogoProveedorDTO;
import com.server.constructionapp.DTO.ProveedorDTO;
import com.server.constructionapp.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class ProveedorREST {
    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/proveedor")
    public ResponseEntity<ProveedorDTO> crearProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO nuevoProveedorDTO = proveedorService.crearProveedor(proveedorDTO);
        return new ResponseEntity<>(nuevoProveedorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<ProveedorDTO> obtenerProveedor(@PathVariable Long id) {
        ProveedorDTO proveedorDTO = proveedorService.obtenerProveedor(id);
        return ResponseEntity.ok(proveedorDTO);
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorDTO>> obtenerTodosLosProveedores() {
        List<ProveedorDTO> proveedorDTOs = proveedorService.obtenerTodosLosProveedores();
        return ResponseEntity.ok(proveedorDTOs);
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<ProveedorDTO> actualizarProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        proveedorDTO.setId(id);
        ProveedorDTO proveedorActualizado = proveedorService.actualizarProveedor(proveedorDTO);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proveedor/{proveedorId}/catalogos")
    public ResponseEntity<List<CatalogoProveedorDTO>> obtenerCatalogosDeProveedor(@PathVariable Long proveedorId) {
        List<CatalogoProveedorDTO> catalogos = proveedorService.obtenerCatalogosDeProveedor(proveedorId);
        return ResponseEntity.ok(catalogos);
    }
}
