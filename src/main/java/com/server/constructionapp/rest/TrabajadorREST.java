package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.TrabajadorDTO;
import com.server.constructionapp.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://constructora-code-frontend.vercel.app"})
@RestController
@RequestMapping("/api/v2")
public class TrabajadorREST {
    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("/trabajador")
    public ResponseEntity<TrabajadorDTO> crearTrabajador(@RequestBody TrabajadorDTO trabajadorDTO) {
        TrabajadorDTO nuevoTrabajadorDTO = trabajadorService.crearTrabajador(trabajadorDTO);
        return new ResponseEntity<>(nuevoTrabajadorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/trabajador/{id}")
    public ResponseEntity<TrabajadorDTO> obtenerTrabajador(@PathVariable Long id) {
        TrabajadorDTO trabajadorDTO = trabajadorService.obtenerTrabajador(id);
        return ResponseEntity.ok(trabajadorDTO);
    }

    @GetMapping("/trabajadores")
    public ResponseEntity<List<TrabajadorDTO>> obtenerTodosLosTrabajadores() {
        List<TrabajadorDTO> trabajadorDTOs = trabajadorService.obtenerTodosLosTrabajadores();
        return ResponseEntity.ok(trabajadorDTOs);
    }

    @PutMapping("/trabajadores/{id}")
    public ResponseEntity<TrabajadorDTO> actualizarTrabajador(@PathVariable Long id, @RequestBody TrabajadorDTO trabajadorDTO) {
        trabajadorDTO.setId(id);
        TrabajadorDTO trabajadorActualizado = trabajadorService.actualizarTrabajador(trabajadorDTO);
        return ResponseEntity.ok(trabajadorActualizado);
    }

    @DeleteMapping("/trabajadores/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id) {
        trabajadorService.eliminarTrabajador(id);
        return ResponseEntity.noContent().build();
    }
}
