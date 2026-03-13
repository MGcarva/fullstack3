package com.ecommerce.productos.controller;

import com.ecommerce.productos.dto.CategoriaDTO;
import com.ecommerce.productos.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para Categorías.
 * Equivalente a CategoriaListCreateView de Django.
 *
 * Endpoints:
 *   GET  /api/categorias/  → Lista de categorías
 *   POST /api/categorias/  → Crear nueva categoría
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@RequestBody CategoriaDTO dto) {
        CategoriaDTO creada = categoriaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}
