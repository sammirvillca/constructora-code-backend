package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.ContratoPlanoDTO;
import com.server.constructionapp.service.ContratoPlanoService;
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
public class ContratoPlanoREST {
    @Autowired
    private ContratoPlanoService contratoPlanoService;

    @PostMapping(value = "/contrato-plano", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratoPlanoDTO> crearContratoPlano(
            @RequestParam("description") String description,
            @RequestParam("deadlines") String deadlines,
            @RequestParam("prices") Long prices,
            @RequestParam("contractDocument") MultipartFile contractDocument,
            @RequestParam("datosClienteId") Long datosClienteId,
            @RequestParam("dibujoPlanoId") Long dibujoPlanoId,
            @RequestParam("codContrato") String codContrato
    ) throws IOException {
        ContratoPlanoDTO contratoPlanoDTO = new ContratoPlanoDTO();
        contratoPlanoDTO.setDescription(description);
        contratoPlanoDTO.setDeadlines(deadlines);
        contratoPlanoDTO.setPrices(prices);
        contratoPlanoDTO.setContractDocument(contractDocument.getBytes());
        contratoPlanoDTO.setDatosClienteId(datosClienteId);
        contratoPlanoDTO.setDibujoPlanoId(dibujoPlanoId);
        contratoPlanoDTO.setCodContrato(codContrato);

        ContratoPlanoDTO nuevoContratoPlanoDTO = contratoPlanoService.crearContratoPlano(contratoPlanoDTO);
        return new ResponseEntity<>(nuevoContratoPlanoDTO, HttpStatus.CREATED);
    }

    // Resto de los métodos del controlador (obtener, actualizar, eliminar)

    @GetMapping("/contrato-plano/{id}")
    public ResponseEntity<ContratoPlanoDTO> obtenerContratoPlano(@PathVariable Long id) {
        ContratoPlanoDTO contratoPlanoDTO = contratoPlanoService.obtenerContratoPlano(id);
        return ResponseEntity.ok(contratoPlanoDTO);
    }

    @GetMapping("/contratos-planos")
    public ResponseEntity<List<ContratoPlanoDTO>> obtenerTodosLosContratosPlano() {
        List<ContratoPlanoDTO> contratoPlanoDTOs = contratoPlanoService.obtenerTodosLosContratosPlano();
        return ResponseEntity.ok(contratoPlanoDTOs);
    }

    @PutMapping("/contrato-planos/{id}")
    public ResponseEntity<ContratoPlanoDTO> actualizarContratoPlano(
            @PathVariable Long id,
            @RequestParam("description") String description,
            @RequestParam("deadlines") String deadlines,
            @RequestParam("prices") Long prices,
            @RequestParam(value = "contractDocument", required = false) MultipartFile contractDocument,
            @RequestParam("datosClienteId") Long datosClienteId,
            @RequestParam("dibujoPlanoId") Long dibujoPlanoId,
            @RequestParam("codContrato") String codContrato
    ) throws IOException {
        ContratoPlanoDTO contratoPlanoDTO = new ContratoPlanoDTO();
        contratoPlanoDTO.setId(id);
        contratoPlanoDTO.setDescription(description);
        contratoPlanoDTO.setDeadlines(deadlines);
        contratoPlanoDTO.setPrices(prices);
        if (contractDocument != null) {
            contratoPlanoDTO.setContractDocument(contractDocument.getBytes());
        }
        contratoPlanoDTO.setDatosClienteId(datosClienteId);
        contratoPlanoDTO.setDibujoPlanoId(dibujoPlanoId);
        contratoPlanoDTO.setCodContrato(codContrato);

        ContratoPlanoDTO contratoPlanoActualizado = contratoPlanoService.actualizarContratoPlano(contratoPlanoDTO);
        return ResponseEntity.ok(contratoPlanoActualizado);
    }

    @DeleteMapping("/contrato-planos/{id}")
    public ResponseEntity<Void> eliminarContratoPlano(@PathVariable Long id) {
        contratoPlanoService.eliminarContratoPlano(id);
        return ResponseEntity.noContent().build();
    }

}


