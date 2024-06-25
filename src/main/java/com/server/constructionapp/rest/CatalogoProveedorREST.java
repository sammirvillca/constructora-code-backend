package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.CatalogoProveedorDTO;
import com.server.constructionapp.service.CatalogoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class CatalogoProveedorREST {
    @Autowired
    private CatalogoProveedorService catalogoProveedorService;

    @PostMapping("/catalogo-proveedor")
    public ResponseEntity<CatalogoProveedorDTO> crearCatalogoProveedor(@RequestBody CatalogoProveedorDTO catalogoProveedorDTO) {
        CatalogoProveedorDTO nuevoCatalogoProveedorDTO = catalogoProveedorService.crearCatalogoProveedor(catalogoProveedorDTO);
        return new ResponseEntity<>(nuevoCatalogoProveedorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/catalogo-proveedor/{id}")
    public ResponseEntity<CatalogoProveedorDTO> obtenerCatalogoProveedor(@PathVariable Long id) {
        CatalogoProveedorDTO catalogoProveedorDTO = catalogoProveedorService.obtenerCatalogoProveedor(id);
        return ResponseEntity.ok(catalogoProveedorDTO);
    }

    @GetMapping("/catalogos-proveedores")
    public ResponseEntity<List<CatalogoProveedorDTO>> obtenerTodosLosCatalogosProveedor() {
        List<CatalogoProveedorDTO> catalogoProveedorDTOs = catalogoProveedorService.obtenerTodosLosCatalogosProveedor();
        return ResponseEntity.ok(catalogoProveedorDTOs);
    }

    @PutMapping("/catalogos-proveedor/{id}")
    public ResponseEntity<CatalogoProveedorDTO> actualizarCatalogoProveedor(@PathVariable Long id, @RequestBody CatalogoProveedorDTO catalogoProveedorDTO) {
        catalogoProveedorDTO.setId(id);
        CatalogoProveedorDTO catalogoProveedorActualizado = catalogoProveedorService.actualizarCatalogoProveedor(catalogoProveedorDTO);
        return ResponseEntity.ok(catalogoProveedorActualizado);
    }

    @DeleteMapping("/catalogos-proveedor/{id}")
    public ResponseEntity<Void> eliminarCatalogoProveedor(@PathVariable Long id) {
        catalogoProveedorService.eliminarCatalogoProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalogos-proveedor/proveedor/{proveedorId}")
    public ResponseEntity<List<CatalogoProveedorDTO>> obtenerCatalogosProveedorPorProveedorId(@PathVariable Long proveedorId) {
        List<CatalogoProveedorDTO> catalogoProveedorDTOs = catalogoProveedorService.obtenerCatalogosProveedorPorId(proveedorId);
        return ResponseEntity.ok(catalogoProveedorDTOs);
    }
}
