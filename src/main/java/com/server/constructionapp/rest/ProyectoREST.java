package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.*;
import com.server.constructionapp.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class ProyectoREST {
    @Autowired
    private ProyectoService proyectoService;

    @PostMapping("/proyecto")
    public ResponseEntity<ProyectoDTO> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO nuevoProyectoDTO = proyectoService.crearProyecto(proyectoDTO);
        return new ResponseEntity<>(nuevoProyectoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<ProyectoDTO> obtenerProyecto(@PathVariable Long id) {
        ProyectoDTO proyectoDTO = proyectoService.obtenerProyecto(id);
        return ResponseEntity.ok(proyectoDTO);
    }

    @GetMapping("/proyectos")
    public ResponseEntity<List<ProyectoDTO>> obtenerTodosLosProyectos() {
        List<ProyectoDTO> proyectoDTOs = proyectoService.obtenerTodosLosProyectos();
        return ResponseEntity.ok(proyectoDTOs);
    }

    @PutMapping("/proyectos/{id}")
    public ResponseEntity<ProyectoDTO> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoDTO proyectoDTO) {
        proyectoDTO.setId(id);
        ProyectoDTO proyectoActualizado = proyectoService.actualizarProyecto(proyectoDTO);
        return ResponseEntity.ok(proyectoActualizado);
    }

    @DeleteMapping("/proyectos/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/proyecto/{id}/trabajadores")
    public ResponseEntity<Void> asignarTrabajadoresAProyecto(@PathVariable Long id, @RequestBody List<Long> trabajadoresIds) {
        proyectoService.asignarTrabajadoresAProyecto(id, trabajadoresIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/proyecto/{id}/ordenes-pedido")
    public ResponseEntity<Void> asignarOrdenesPedidoAProyecto(@PathVariable Long id, @RequestBody List<Long> ordenesPedidoIds) {
        proyectoService.asignarOrdenesPedidoAProyecto(id, ordenesPedidoIds);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/proyecto/{id}/trabajadores")
    public ResponseEntity<List<TrabajadorDTO>> obtenerTrabajadoresAsignadosAProyecto(@PathVariable Long id) {
        List<TrabajadorDTO> trabajadoresDTO = proyectoService.obtenerTrabajadoresAsignadosAProyecto(id);
        return ResponseEntity.ok(trabajadoresDTO);
    }

    @GetMapping("/proyecto/{id}/ordenes-pedido-material")
    public ResponseEntity<List<OrdenPedidoMaterialDTO>> obtenerOrdenesPedidoMaterialPorProyecto(@PathVariable Long id) {
        List<OrdenPedidoMaterialDTO> ordenesPedidoMaterialDTO = proyectoService.obtenerOrdenesPedidoMaterialPorProyecto(id);
        return ResponseEntity.ok(ordenesPedidoMaterialDTO);
    }

    @GetMapping("/cronogramas-disponibles")
    public ResponseEntity<List<CronogramaDTO>> obtenerCronogramasDisponibles() {
        List<CronogramaDTO> cronogramasDisponibles = proyectoService.obtenerCronogramasDisponibles();
        return ResponseEntity.ok(cronogramasDisponibles);
    }

    @GetMapping("/trabajadores/ingciviles")
    public ResponseEntity<List<TrabajadorDTO>> obtenerTrabajadoresIngCivil(@RequestParam String rol) {
        if (rol.equals("Ing. Civil")) {
            List<TrabajadorDTO> trabajadoresIngCivil = proyectoService.obtenerTrabajadoresIngCivil();
            return ResponseEntity.ok(trabajadoresIngCivil);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/planos-sin-vincular")
    public ResponseEntity<List<DibujoPlanoDTO>> obtenerPlanosSinVincular() {
        List<DibujoPlanoDTO> planosSinVincular = proyectoService.obtenerPlanosSinVincular();
        return ResponseEntity.ok(planosSinVincular);
    }

    @GetMapping("/proyectos-no-vinculados")
    public ResponseEntity<List<ProyectoDTO>> obtenerProyectosNoVinculados() {
        List<ProyectoDTO> proyectosNoVinculados = proyectoService.obtenerProyectosNoVinculados();
        return ResponseEntity.ok(proyectosNoVinculados);
    }

    @PutMapping("/proyecto/{id}/desvincular-trabajadores")
    public ResponseEntity<Void> desvincularTrabajadoresDeProyecto(@PathVariable Long id, @RequestBody List<Long> trabajadoresIds) {
        proyectoService.desvincularTrabajadoresDeProyecto(id, trabajadoresIds);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/orden-pedido-materiales/{id}")
    public ResponseEntity<OrdenPedidoMaterialDTO> actualizarOrdenPedidoMaterial(@PathVariable Long id, @RequestBody OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        OrdenPedidoMaterialDTO ordenPedidoMaterialActualizada = proyectoService.actualizarOrdenPedidoMaterial(id, ordenPedidoMaterialDTO);
        return ResponseEntity.ok(ordenPedidoMaterialActualizada);
    }

    @DeleteMapping("/orden-pedido-materiales/{id}")
    public ResponseEntity<Void> eliminarOrdenPedidoMaterial(@PathVariable Long id) {
        proyectoService.eliminarOrdenPedidoMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
