package com.ecommerce.productos.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PedidoDTO {
    private Long id;
    private Long usuarioId; // Solo enviamos el ID del usuario, no toda la entidad
    private String nombreUsuario; // Un dato extra útil para el frontend
    private LocalDateTime fechaPedido;
    private Double total;
    private String estado;
}