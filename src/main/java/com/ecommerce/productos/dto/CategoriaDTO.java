package com.ecommerce.productos.dto;

import lombok.*;

/**
 * DTO para respuesta de Categoria.
 * Equivalente al CategoriaSerializer de Django REST Framework.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
