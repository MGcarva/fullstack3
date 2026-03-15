package com.ecommerce.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private LocalDateTime fechaPedido = LocalDateTime.now();

    @NotNull(message = "El total es obligatorio")
    @Min(value = 0, message = "El total debe ser mayor a 0")
    private Double total;

    // Estados sugeridos: PENDIENTE, PAGADO, ENVIADO, ENTREGADO
    private String estado = "PENDIENTE";
}