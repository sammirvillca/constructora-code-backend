package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.DibujoPlanoDTO;
import com.server.constructionapp.DTO.PrerequisitoPlanoDTO;
import com.server.constructionapp.service.DibujoPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class DibujoPlanoREST {
    @Autowired
    private DibujoPlanoService dibujoPlanoService;

    @PostMapping("/dibujo-plano")
    public ResponseEntity<DibujoPlanoDTO> crearDibujoPlano(@RequestBody DibujoPlanoDTO dibujoPlanoDTO) {
        DibujoPlanoDTO nuevoDibujoPlanoDTO = dibujoPlanoService.crearDibujoPlano(dibujoPlanoDTO);
        return new ResponseEntity<>(nuevoDibujoPlanoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/dibujo-plano/{id}")
    public ResponseEntity<DibujoPlanoDTO> obtenerDibujoPlano(@PathVariable Long id) {
        DibujoPlanoDTO dibujoPlanoDTO = dibujoPlanoService.obtenerDibujoPlanoPorId(id);
        return ResponseEntity.ok(dibujoPlanoDTO);
    }

    @GetMapping("/dibujos-planos")
    public ResponseEntity<List<DibujoPlanoDTO>> obtenerTodosLosDibujosPlano() {
        List<DibujoPlanoDTO> dibujoPlanoDTOs = dibujoPlanoService.obtenerTodosLosDibujosPlano();
        return ResponseEntity.ok(dibujoPlanoDTOs);
    }

    @PutMapping("/dibujo-planos/{id}")
    public ResponseEntity<DibujoPlanoDTO> actualizarDibujoPlano(@PathVariable Long id, @RequestBody DibujoPlanoDTO dibujoPlanoDTO) {
        if (!id.equals(dibujoPlanoDTO.getId())) {
            throw new IllegalArgumentException("ID en la URL no coincide con el ID en el cuerpo de la solicitud");
        }
        DibujoPlanoDTO dibujoPlanoActualizado = dibujoPlanoService.actualizarDibujoPlano(dibujoPlanoDTO);
        return ResponseEntity.ok(dibujoPlanoActualizado);
    }

    @DeleteMapping("/dibujo-planos/{id}")
    public ResponseEntity<Void> eliminarDibujoPlano(@PathVariable Long id) {
        dibujoPlanoService.eliminarDibujoPlano(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dibujo-plano-no-vinculados")
    public ResponseEntity<List<DibujoPlanoDTO>> obtenerDibujoPlanoNoVinculados() {
        List<DibujoPlanoDTO> dibujoPlanoDTOS = dibujoPlanoService.obtenerDibujoPlanoNoVinculados();
        return ResponseEntity.ok(dibujoPlanoDTOS);
    }


}
