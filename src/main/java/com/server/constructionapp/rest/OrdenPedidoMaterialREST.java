package com.server.constructionapp.rest;

import com.server.constructionapp.DTO.OrdenPedidoMaterialDTO;
import com.server.constructionapp.service.OrdenPedidoMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v2")
public class OrdenPedidoMaterialREST {
    @Autowired
    private OrdenPedidoMaterialService ordenPedidoMaterialService;

    @PostMapping("/orden-pedido-material")
    public ResponseEntity<OrdenPedidoMaterialDTO> crearOrdenPedidoMaterial(@RequestBody OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        OrdenPedidoMaterialDTO nuevaOrdenPedidoMaterialDTO = ordenPedidoMaterialService.crearOrdenPedidoMaterial(ordenPedidoMaterialDTO);
        return new ResponseEntity<>(nuevaOrdenPedidoMaterialDTO, HttpStatus.CREATED);
    }

    @GetMapping("/orden-pedido-material/{id}")
    public ResponseEntity<OrdenPedidoMaterialDTO> obtenerOrdenPedidoMaterial(@PathVariable Long id) {
        OrdenPedidoMaterialDTO ordenPedidoMaterialDTO = ordenPedidoMaterialService.obtenerOrdenPedidoMaterial(id);
        return ResponseEntity.ok(ordenPedidoMaterialDTO);
    }

    @GetMapping("/ordenes-pedidos")
    public ResponseEntity<List<OrdenPedidoMaterialDTO>> obtenerTodasLasOrdenesPedidoMaterial() {
        List<OrdenPedidoMaterialDTO> ordenPedidoMaterialDTOs = ordenPedidoMaterialService.obtenerTodasLasOrdenesPedidoMaterial();
        return ResponseEntity.ok(ordenPedidoMaterialDTOs);
    }

    @PutMapping("/orden-pedido/{id}")
    public ResponseEntity<OrdenPedidoMaterialDTO> actualizarOrdenPedidoMaterial(@PathVariable Long id, @RequestBody OrdenPedidoMaterialDTO ordenPedidoMaterialDTO) {
        ordenPedidoMaterialDTO.setId(id);
        OrdenPedidoMaterialDTO ordenPedidoMaterialActualizada = ordenPedidoMaterialService.actualizarOrdenPedidoMaterial(ordenPedidoMaterialDTO);
        return ResponseEntity.ok(ordenPedidoMaterialActualizada);
    }

    @DeleteMapping("/orden-pedido/{id}")
    public ResponseEntity<Void> eliminarOrdenPedidoMaterial(@PathVariable Long id) {
        ordenPedidoMaterialService.eliminarOrdenPedidoMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
