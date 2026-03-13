package com.ecommerce.productos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Endpoint de salud del microservicio.
 * Equivalente al health_check de Django.
 *
 * GET /api/health/ → Estado del servicio
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "servicio", "Microservicio de Productos (Spring Boot)",
                "version", "1.0.0"
        ));
    }
}
