package com.ecommerce.productos.controller;

import com.ecommerce.productos.dto.ProductoCreateDTO;
import com.ecommerce.productos.dto.ProductoDTO;
import com.ecommerce.productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para Productos.
 * Equivalente a ProductoListCreateView y ProductoDetailView de Django.
 *
 * Endpoints:
 *   GET    /api/productos/       → Lista de productos (JSON)
 *   POST   /api/productos/       → Crear nuevo producto
 *   GET    /api/productos/{id}   → Detalle de un producto
 *   PUT    /api/productos/{id}   → Actualizar un producto
 *   DELETE /api/productos/{id}   → Eliminar un producto
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoCreateDTO dto) {
        ProductoDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id,
                                                   @Valid @RequestBody ProductoCreateDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
