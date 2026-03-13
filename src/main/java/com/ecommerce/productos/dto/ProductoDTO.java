package com.ecommerce.productos.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para respuesta de Producto (lectura).
 * Equivalente al ProductoSerializer de Django REST Framework.
 * Incluye el campo 'categoriaNombre' como dato anidado.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private Long categoriaId;
    private String categoriaNombre;
    private Boolean disponible;
    private String imagenUrl;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
