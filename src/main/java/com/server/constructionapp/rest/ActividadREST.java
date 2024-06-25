package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.ActividadDTO;
import com.server.constructionapp.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class ActividadREST {
    @Autowired
    private ActividadService actividadService;

    @PostMapping("/cronogramas/{cronogramaId}/actividad")
    public ResponseEntity<ActividadDTO> crearActividad(
            @PathVariable Long cronogramaId,
            @RequestBody ActividadDTO actividadDTO
    ) {
        ActividadDTO nuevaActividadDTO = actividadService.crearActividad(cronogramaId, actividadDTO);
        return new ResponseEntity<>(nuevaActividadDTO, HttpStatus.CREATED);
    }

    @GetMapping("/actividades/{id}")
    public ResponseEntity<ActividadDTO> obtenerActividad(@PathVariable Long id) {
        ActividadDTO actividadDTO = actividadService.obtenerActividad(id);
        return ResponseEntity.ok(actividadDTO);
    }

    @GetMapping("/cronogramas/{cronogramaId}/actividades")
    public ResponseEntity<List<ActividadDTO>> obtenerActividadesPorCronogramaId(@PathVariable Long cronogramaId) {
        List<ActividadDTO> actividades = actividadService.obtenerActividadesPorCronogramaId(cronogramaId);
        return ResponseEntity.ok(actividades);
    }

    @PutMapping("/actividades/{id}")
    public ResponseEntity<ActividadDTO> actualizarActividad(@PathVariable Long id, @RequestBody ActividadDTO actividadDTO) {
        ActividadDTO actividadActualizada = actividadService.actualizarActividad(id, actividadDTO);
        return ResponseEntity.ok(actividadActualizada);
    }

    @DeleteMapping("/actividades/{id}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Long id) {
        actividadService.eliminarActividad(id);
        return ResponseEntity.noContent().build();
    }

}
