package com.ecommerce.productos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoCreateDTO {
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El total es obligatorio")
    @Min(value = 0, message = "El total debe ser mayor a 0")
    private Double total;
}