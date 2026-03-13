package com.ecommerce.productos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * DTO para creación/actualización de Producto.
 * Equivalente al ProductoCreateSerializer de Django REST Framework.
 * Incluye validaciones (precio > 0, nombre obligatorio, etc.).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoCreateDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 200, message = "El nombre no puede superar los 200 caracteres.")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0.")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener máximo 8 dígitos enteros y 2 decimales.")
    private BigDecimal precio;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    private Integer stock = 0;

    private Long categoriaId;

    private Boolean disponible = true;

    @Size(max = 500, message = "La URL de imagen no puede superar los 500 caracteres.")
    private String imagenUrl;
}
