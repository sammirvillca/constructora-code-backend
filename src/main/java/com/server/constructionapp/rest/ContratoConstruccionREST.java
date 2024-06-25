package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.ContratoConstruccionDTO;
import com.server.constructionapp.service.ContratoConstruccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class ContratoConstruccionREST {
    @Autowired
    private ContratoConstruccionService contratoConstruccionService;

    @PostMapping(value = "/contrato-construccion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratoConstruccionDTO> crearContratoConstruccion(
            @RequestParam("constructionDoc") MultipartFile constructionDoc,
            @RequestParam("proyectoId") Long proyectoId,
            @RequestParam("codContConstruccion") String codContConstruccion
    ) throws IOException {
        ContratoConstruccionDTO contratoConstruccionDTO = new ContratoConstruccionDTO();
        contratoConstruccionDTO.setConstructionDoc(constructionDoc.getBytes());
        contratoConstruccionDTO.setProyectoId(proyectoId);
        contratoConstruccionDTO.setCodContConstruccion(codContConstruccion);

        ContratoConstruccionDTO nuevoContratoConstruccionDTO = contratoConstruccionService.crearContratoConstruccion(contratoConstruccionDTO);
        return new ResponseEntity<>(nuevoContratoConstruccionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/contrato-construccion/{id}")
    public ResponseEntity<ContratoConstruccionDTO> obtenerContratoConstruccion(@PathVariable Long id) {
        ContratoConstruccionDTO contratoConstruccionDTO = contratoConstruccionService.obtenerContratoConstruccion(id);
        return ResponseEntity.ok(contratoConstruccionDTO);
    }

    @GetMapping("/contratos-construcciones")
    public ResponseEntity<List<ContratoConstruccionDTO>> obtenerTodosLosContratosConstruccion() {
        List<ContratoConstruccionDTO> contratoConstruccionDTOs = contratoConstruccionService.obtenerTodosLosContratosConstruccion();
        return ResponseEntity.ok(contratoConstruccionDTOs);
    }

    @PutMapping("/contrato-construcciones/{id}")
    public ResponseEntity<ContratoConstruccionDTO> actualizarContratoConstruccion(
            @PathVariable Long id,
            @RequestParam(value = "constructionDoc", required = false) MultipartFile constructionDoc,
            @RequestParam("proyectoId") Long proyectoId,
            @RequestParam("codContConstruccion") String codContConstruccion
    ) throws IOException {
        ContratoConstruccionDTO contratoConstruccionDTO = new ContratoConstruccionDTO();
        contratoConstruccionDTO.setId(id);
        if (constructionDoc != null) {
            contratoConstruccionDTO.setConstructionDoc(constructionDoc.getBytes());
        }
        contratoConstruccionDTO.setProyectoId(proyectoId);
        contratoConstruccionDTO.setCodContConstruccion(codContConstruccion);

        ContratoConstruccionDTO contratoConstruccionActualizado = contratoConstruccionService.actualizarContratoConstruccion(contratoConstruccionDTO);
        return ResponseEntity.ok(contratoConstruccionActualizado);
    }

    @DeleteMapping("/contrato-construcciones/{id}")
    public ResponseEntity<Void> eliminarContratoConstruccion(@PathVariable Long id) {
        contratoConstruccionService.eliminarContratoConstruccion(id);
        return ResponseEntity.noContent().build();
    }
}
