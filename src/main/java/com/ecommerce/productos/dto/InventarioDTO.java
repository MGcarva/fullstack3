package com.ecommerce.productos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InventarioDTO {
    private Long id;
    private Long productoId;
    private Integer cantidadDisponible;
    private String ubicacion;
    private LocalDateTime ultimaActualizacion;
}