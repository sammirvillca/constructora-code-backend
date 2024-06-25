package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.EntregaDTO;
import com.server.constructionapp.DTO.ProyectoDTO;
import com.server.constructionapp.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class EntregaREST {
    @Autowired
    private EntregaService entregaService;

    @PostMapping("/entrega")
    public ResponseEntity<EntregaDTO> crearEntrega(@RequestBody EntregaDTO entregaDTO) {
        EntregaDTO nuevaEntregaDTO = entregaService.crearEntrega(entregaDTO);
        return new ResponseEntity<>(nuevaEntregaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/entrega/{id}")
    public ResponseEntity<EntregaDTO> obtenerEntrega(@PathVariable Long id) {
        EntregaDTO entregaDTO = entregaService.obtenerEntrega(id);
        return ResponseEntity.ok(entregaDTO);
    }

    @GetMapping("/entregas")
    public ResponseEntity<List<EntregaDTO>> obtenerTodasLasEntregas() {
        List<EntregaDTO> entregaDTOs = entregaService.obtenerTodasLasEntregas();
        return ResponseEntity.ok(entregaDTOs);
    }

    @PutMapping("/entregas/{id}")
    public ResponseEntity<EntregaDTO> actualizarEntrega(@PathVariable Long id, @RequestBody EntregaDTO entregaDTO) {
        entregaDTO.setId(id);
        EntregaDTO entregaActualizada = entregaService.actualizarEntrega(entregaDTO);
        return ResponseEntity.ok(entregaActualizada);
    }

    @DeleteMapping("/entregas/{id}")
    public ResponseEntity<Void> eliminarEntrega(@PathVariable Long id) {
        entregaService.eliminarEntrega(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proyectos-sin-entrega")
    public ResponseEntity<List<ProyectoDTO>> obtenerProyectosSinEntrega() {
        List<ProyectoDTO> proyectos = entregaService.obtenerProyectosSinEntrega();
        return ResponseEntity.ok(proyectos);
    }
}
