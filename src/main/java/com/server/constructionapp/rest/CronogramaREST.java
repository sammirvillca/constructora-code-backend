package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.CronogramaDTO;
import com.server.constructionapp.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://constructora-code-frontend.vercel.app"})
@RestController
@RequestMapping("/api/v2")
public class CronogramaREST {
    @Autowired
    private CronogramaService cronogramaService;

    @PostMapping("/cronograma")
    public ResponseEntity<CronogramaDTO> crearCronograma(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("codCronograma") String codCronograma
    ) {
        CronogramaDTO cronogramaDTO = new CronogramaDTO();
        cronogramaDTO.setStartDate(startDate);
        cronogramaDTO.setEndDate(endDate);
        cronogramaDTO.setCodCronograma(codCronograma);

        CronogramaDTO nuevoCronogramaDTO = cronogramaService.crearCronograma(cronogramaDTO);
        return new ResponseEntity<>(nuevoCronogramaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/cronograma/{id}")
    public ResponseEntity<CronogramaDTO> obtenerCronograma(@PathVariable Long id) {
        CronogramaDTO cronogramaDTO = cronogramaService.obtenerCronograma(id);
        return ResponseEntity.ok(cronogramaDTO);
    }

    @GetMapping("/cronogramas")
    public ResponseEntity<List<CronogramaDTO>> obtenerTodosLosCronogramas() {
        List<CronogramaDTO> cronogramaDTOs = cronogramaService.obtenerTodosLosCronogramas();
        return ResponseEntity.ok(cronogramaDTOs);
    }

    @PutMapping("/cronogramas/{id}")
    public ResponseEntity<CronogramaDTO> actualizarCronograma(
            @PathVariable Long id,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate,
            @RequestParam("codCronograma") String codCronograma
    ) {
        CronogramaDTO cronogramaDTO = new CronogramaDTO();
        cronogramaDTO.setId(id);
        cronogramaDTO.setStartDate(startDate);
        cronogramaDTO.setEndDate(endDate);
        cronogramaDTO.setCodCronograma(codCronograma);

        CronogramaDTO cronogramaActualizado = cronogramaService.actualizarCronograma(cronogramaDTO);
        return ResponseEntity.ok(cronogramaActualizado);
    }

    @DeleteMapping("/cronogramas/{id}")
    public ResponseEntity<Void> eliminarCronograma(@PathVariable Long id) {
        cronogramaService.eliminarCronograma(id);
        return ResponseEntity.noContent().build();
    }
}
