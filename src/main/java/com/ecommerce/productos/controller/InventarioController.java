package com.ecommerce.productos.controller;

import com.ecommerce.productos.dto.InventarioDTO;
import com.ecommerce.productos.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> getAllInventarios() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioDTO> getInventarioByProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(inventarioService.findByProductoId(productoId));
    }

    @PutMapping("/producto/{productoId}")
    public ResponseEntity<InventarioDTO> updateStock(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(inventarioService.updateStock(productoId, cantidad));
    }
}