package com.ecommerce.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del Microservicio de Productos.
 * Equivalente al manage.py + settings.py del proyecto Django.
 */
@SpringBootApplication
public class ProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosApplication.class, args);
    }
}
