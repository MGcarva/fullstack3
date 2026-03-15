package com.ecommerce.productos.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}