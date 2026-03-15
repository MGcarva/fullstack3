package com.ecommerce.productos.service;

import com.ecommerce.productos.dto.InventarioDTO;
import com.ecommerce.productos.model.Inventario;
import com.ecommerce.productos.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Transactional(readOnly = true)
    public List<InventarioDTO> findAll() {
        return inventarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InventarioDTO findByProductoId(Long productoId) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        return convertToDTO(inventario);
    }

    @Transactional
    public InventarioDTO updateStock(Long productoId, Integer cantidad) {
        Inventario inventario = inventarioRepository.findByProductoId(productoId)
                .orElse(new Inventario(null, productoId, 0, "Bodega Central", LocalDateTime.now()));
        
        inventario.setCantidadDisponible(cantidad);
        inventario.setUltimaActualizacion(LocalDateTime.now());
        
        return convertToDTO(inventarioRepository.save(inventario));
    }

    private InventarioDTO convertToDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());
        dto.setProductoId(inventario.getProductoId());
        dto.setCantidadDisponible(inventario.getCantidadDisponible());
        dto.setUbicacion(inventario.getUbicacion());
        dto.setUltimaActualizacion(inventario.getUltimaActualizacion());
        return dto;
    }
}