package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.ProcesoConstructivoDTO;
import com.server.constructionapp.service.ProcesoConstructivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class ProcesoConstructivoREST {
    @Autowired
    private ProcesoConstructivoService procesoConstructivoService;

    @PostMapping("/proceso-constructivo")
    public ResponseEntity<ProcesoConstructivoDTO> crearProcesoConstructivo(@RequestBody ProcesoConstructivoDTO procesoConstructivoDTO) {
        ProcesoConstructivoDTO nuevoProcesoConstructivoDTO = procesoConstructivoService.crearProcesoConstructivo(procesoConstructivoDTO);
        return new ResponseEntity<>(nuevoProcesoConstructivoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/proceso-constructivo/{id}")
    public ResponseEntity<ProcesoConstructivoDTO> obtenerProcesoConstructivo(@PathVariable Long id) {
        ProcesoConstructivoDTO procesoConstructivoDTO = procesoConstructivoService.obtenerProcesoConstructivo(id);
        return ResponseEntity.ok(procesoConstructivoDTO);
    }

    @GetMapping("/procesos-constructivos")
    public ResponseEntity<List<ProcesoConstructivoDTO>> obtenerTodosLosProcesosConstructivos() {
        List<ProcesoConstructivoDTO> procesoConstructivoDTOs = procesoConstructivoService.obtenerTodosLosProcesosConstructivos();
        return ResponseEntity.ok(procesoConstructivoDTOs);
    }

    @PutMapping("/procesos-constructivo/{id}")
    public ResponseEntity<ProcesoConstructivoDTO> actualizarProcesoConstructivo(@PathVariable Long id, @RequestBody ProcesoConstructivoDTO procesoConstructivoDTO) {
        procesoConstructivoDTO.setId(id);
        ProcesoConstructivoDTO procesoConstructivoActualizado = procesoConstructivoService.actualizarProcesoConstructivo(procesoConstructivoDTO);
        return ResponseEntity.ok(procesoConstructivoActualizado);
    }

    @DeleteMapping("/procesos-constructivo/{id}")
    public ResponseEntity<Void> eliminarProcesoConstructivo(@PathVariable Long id) {
        procesoConstructivoService.eliminarProcesoConstructivo(id);
        return ResponseEntity.noContent().build();
    }
}
