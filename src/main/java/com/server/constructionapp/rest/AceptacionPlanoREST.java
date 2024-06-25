package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.AceptacionPlanoDTO;
import com.server.constructionapp.service.AceptacionPanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://constructora-code-frontend.vercel.app"})
@RestController
@RequestMapping("/api/v2")
public class AceptacionPlanoREST {
    @Autowired
    private AceptacionPanoService aceptacionPanoService;

    @PostMapping("/aceptacion-plano")
    public ResponseEntity<AceptacionPlanoDTO> crearAceptacionPlano(@RequestBody AceptacionPlanoDTO aceptacionPlanoDTO){
        AceptacionPlanoDTO nuevaAceptacionPlanoDTO = aceptacionPanoService.crearAceptacionPlano(aceptacionPlanoDTO);
        return new ResponseEntity<>(nuevaAceptacionPlanoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/aceptacion-plano/{id}")
    public ResponseEntity<AceptacionPlanoDTO> obtenerAceptacionPlanoPorId(@PathVariable Long id) {
        AceptacionPlanoDTO aceptacionPlanoDTO = aceptacionPanoService.obtenerAceptacionPorId(id);
        return ResponseEntity.ok(aceptacionPlanoDTO);
    }

    @GetMapping("/aceptacion-planos")
    public ResponseEntity<List<AceptacionPlanoDTO>> obtenerTodasLasAceptacionesPlano() {
        List<AceptacionPlanoDTO> aceptacionPlanoDTOs = aceptacionPanoService.obtenerTodasLasAceptaciones();
        return ResponseEntity.ok(aceptacionPlanoDTOs);
    }

    @PutMapping("/aceptacion-planos/{id}")
    public ResponseEntity<AceptacionPlanoDTO> actualizarAceptacionPlano(@PathVariable Long id, @RequestBody AceptacionPlanoDTO aceptacionPlanoDTO) {
        if (!id.equals(aceptacionPlanoDTO.getId())) {
            throw new IllegalArgumentException("ID en la URL no coincide con el ID en el cuerpo de la solicitud");
        }
        AceptacionPlanoDTO aceptacionPlanoActualizado = aceptacionPanoService.actualizarAceptacionPlano(aceptacionPlanoDTO);
        return ResponseEntity.ok(aceptacionPlanoActualizado);
    }

    @DeleteMapping("/aceptacion-planos/{id}")
    public ResponseEntity<Void> eliminarAceptacionPlano(@PathVariable Long id) {
        aceptacionPanoService.eliminarAceptacionPlano(id);
        return ResponseEntity.noContent().build();
    }
}

