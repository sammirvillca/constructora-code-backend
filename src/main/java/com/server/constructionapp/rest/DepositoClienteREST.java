package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.DepositoClienteDTO;
import com.server.constructionapp.service.DepositoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class DepositoClienteREST {
    @Autowired
    private DepositoClienteService depositoClienteService;

    @PostMapping(value = "/deposito-cliente",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DepositoClienteDTO> crearDepositoCliente(
            @RequestParam("payMethod") String payMethod,
            @RequestParam("payPhoto") MultipartFile payPhoto,
            @RequestParam("payDetails") String payDetails,
            @RequestParam("payDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate payDate,
            @RequestParam("datosClienteId") Long datosClienteId
    ) throws IOException {
        DepositoClienteDTO depositoClienteDTO = new DepositoClienteDTO();
        depositoClienteDTO.setPayMethod(payMethod);
        depositoClienteDTO.setPayPhoto(payPhoto.getBytes());
        depositoClienteDTO.setPayDetails(payDetails);
        depositoClienteDTO.setPayDate(payDate);
        depositoClienteDTO.setDatosClienteId(datosClienteId);

        DepositoClienteDTO nuevoDepositoClienteDTO = depositoClienteService.crearDepositoCliente(depositoClienteDTO);
        return new ResponseEntity<>(nuevoDepositoClienteDTO, HttpStatus.CREATED);
    }

    @GetMapping("/depositos-cliente/{id}")
    public ResponseEntity<DepositoClienteDTO> obtenerDepositoCliente(@PathVariable Long id) {
        DepositoClienteDTO depositoClienteDTO = depositoClienteService.obtenerDepositoCliente(id);
        return ResponseEntity.ok(depositoClienteDTO);
    }

    @GetMapping("/depositos-clientes")
    public ResponseEntity<List<DepositoClienteDTO>> obtenerTodosLosDepositosCliente() {
        List<DepositoClienteDTO> depositoClienteDTOs = depositoClienteService.obtenerTodosLosDepositosCliente();
        return ResponseEntity.ok(depositoClienteDTOs);
    }

    @PutMapping("/depositos-cliente/{id}")
    public ResponseEntity<DepositoClienteDTO> actualizarDepositoCliente(
            @PathVariable Long id,
            @RequestParam("payMethod") String payMethod,
            @RequestParam(value = "payPhoto", required = false) MultipartFile payPhoto,
            @RequestParam("payDetails") String payDetails,
            @RequestParam("payDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate payDate,
            @RequestParam("datosClienteId") Long datosClienteId
    ) throws IOException {
        DepositoClienteDTO depositoClienteDTO = new DepositoClienteDTO();
        depositoClienteDTO.setId(id);
        depositoClienteDTO.setPayMethod(payMethod);
        if (payPhoto != null && !payPhoto.isEmpty()) {
            depositoClienteDTO.setPayPhoto(payPhoto.getBytes());
        }
        depositoClienteDTO.setPayDetails(payDetails);
        depositoClienteDTO.setPayDate(payDate);
        depositoClienteDTO.setDatosClienteId(datosClienteId);

        DepositoClienteDTO depositoClienteActualizado = depositoClienteService.actualizarDepositoCliente(depositoClienteDTO);
        return ResponseEntity.ok(depositoClienteActualizado);
    }

    @DeleteMapping("/depositos-cliente/{id}")
    public ResponseEntity<Void> eliminarDepositoCliente(@PathVariable Long id) {
        depositoClienteService.eliminarDepositoCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clientes/propiedades/depositos/{datosClienteId}")
    public ResponseEntity<List<DepositoClienteDTO>> obtenerDepositosClientePorClienteId(@PathVariable Long datosClienteId){
        List<DepositoClienteDTO> depositoClienteDTOS = depositoClienteService.obtenerDepositosPorId(datosClienteId);
        return ResponseEntity.ok(depositoClienteDTOS);
    }
}
