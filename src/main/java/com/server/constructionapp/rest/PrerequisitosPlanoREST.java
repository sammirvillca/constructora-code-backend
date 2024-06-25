package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.PrerequisitoPlanoDTO;
import com.server.constructionapp.model.PrerequisitoPlano;
import com.server.constructionapp.service.PrerequisitoPlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class PrerequisitosPlanoREST {
    @Autowired
    private PrerequisitoPlanoService prerequisitoPlanoService;

    @GetMapping("/prerequisitos-clientes")
    public ResponseEntity<List<PrerequisitoPlanoDTO>> obtenerTodosLosPrerequisitosPlano() {
        List<PrerequisitoPlanoDTO> prerequisitoPlanoDTOs = prerequisitoPlanoService.obtenerTodosLosPrerequisitosPlano();
        return ResponseEntity.ok(prerequisitoPlanoDTOs);
    }

    @GetMapping("/prerequisito/{id}")
    public ResponseEntity<PrerequisitoPlanoDTO> obtenerPrerequisitoPlano(@PathVariable Long id) {
        PrerequisitoPlanoDTO prerequisitoPlanoDTO = prerequisitoPlanoService.obtenerPrerequisitoPlanoPorId(id);
        return ResponseEntity.ok(prerequisitoPlanoDTO);
    }

    @GetMapping("/clientes/prerequisitos-plano/{clienteId}")
    public ResponseEntity<List<PrerequisitoPlanoDTO>> obtenerPrerequisitosPlanosPorCliente(@PathVariable Long clienteId) {
        List<PrerequisitoPlanoDTO> prerequisitosPlanoDTOs = prerequisitoPlanoService.obtenerPrerequisitosPlanosPorCliente(clienteId);
        return ResponseEntity.ok(prerequisitosPlanoDTOs);
    }

    @PostMapping("/prerequisito-cliente")
    public ResponseEntity<PrerequisitoPlanoDTO> crearPrerequisitoPlano(@RequestBody PrerequisitoPlanoDTO prerequisitoPlanoDTO) {
        PrerequisitoPlanoDTO nuevoPrerequisitoPlanoDTO = prerequisitoPlanoService.crearPrerequisitoPlano(prerequisitoPlanoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrerequisitoPlanoDTO);
    }

    @PutMapping("/prerequisito-clientes/{id}")
    public ResponseEntity<PrerequisitoPlanoDTO> actualizarPrerequisitoPlano(@PathVariable Long id, @RequestBody PrerequisitoPlanoDTO prerequisitoPlanoDTO) {
        prerequisitoPlanoDTO.setId(id);
        PrerequisitoPlanoDTO prerequisitoPlanoActualizadoDTO = prerequisitoPlanoService.actualizarPrerequisitoPlano(prerequisitoPlanoDTO);
        return ResponseEntity.ok(prerequisitoPlanoActualizadoDTO);
    }

    @DeleteMapping("/prerequisito-clientes/{id}")
    public ResponseEntity<Void> eliminarPrerequisitoPlano(@PathVariable Long id) {
        prerequisitoPlanoService.eliminarPrerequisitoPlano(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/prerequisitos-no-vinculados")
    public ResponseEntity<List<PrerequisitoPlanoDTO>> obtenerPrerequisitosPlanoNoVinculados() {
        List<PrerequisitoPlanoDTO> prerequisitosPlanoDTOs = prerequisitoPlanoService.obtenerPrerequisitosPlanoNoVinculados();
        return ResponseEntity.ok(prerequisitosPlanoDTOs);
    }
}
