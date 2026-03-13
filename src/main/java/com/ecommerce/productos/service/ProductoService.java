package com.ecommerce.productos.service;

import com.ecommerce.productos.dto.ProductoCreateDTO;
import com.ecommerce.productos.dto.ProductoDTO;
import com.ecommerce.productos.model.Categoria;
import com.ecommerce.productos.model.Producto;
import com.ecommerce.productos.repository.CategoriaRepository;
import com.ecommerce.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para lógica de negocio de Productos.
 * Contiene las operaciones CRUD completas.
 */
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    /**
     * GET /api/productos/ → Retorna la lista de productos en formato JSON.
     */
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * GET /api/productos/{id} → Detalle de un producto.
     */
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return toDTO(producto);
    }

    /**
     * POST /api/productos/ → Permite agregar un nuevo producto.
     */
    public ProductoDTO crear(ProductoCreateDTO dto) {
        Producto producto = fromCreateDTO(dto);
        Producto guardado = productoRepository.save(producto);
        return toDTO(guardado);
    }

    /**
     * PUT /api/productos/{id} → Actualizar un producto.
     */
    public ProductoDTO actualizar(Long id, ProductoCreateDTO dto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setStock(dto.getStock() != null ? dto.getStock() : 0);
        existente.setDisponible(dto.getDisponible() != null ? dto.getDisponible() : true);
        existente.setImagenUrl(dto.getImagenUrl());

        if (dto.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getCategoriaId()));
            existente.setCategoria(cat);
        } else {
            existente.setCategoria(null);
        }

        Producto actualizado = productoRepository.save(existente);
        return toDTO(actualizado);
    }

    /**
     * DELETE /api/productos/{id} → Eliminar un producto.
     */
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    // ── Mappers ──

    private ProductoDTO toDTO(Producto producto) {
        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoriaId(producto.getCategoria() != null ? producto.getCategoria().getId() : null)
                .categoriaNombre(producto.getCategoria() != null ? producto.getCategoria().getNombre() : null)
                .disponible(producto.getDisponible())
                .imagenUrl(producto.getImagenUrl())
                .fechaCreacion(producto.getFechaCreacion())
                .fechaActualizacion(producto.getFechaActualizacion())
                .build();
    }

    private Producto fromCreateDTO(ProductoCreateDTO dto) {
        Categoria categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        }

        return Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock() != null ? dto.getStock() : 0)
                .categoria(categoria)
                .disponible(dto.getDisponible() != null ? dto.getDisponible() : true)
                .imagenUrl(dto.getImagenUrl())
                .build();
    }
}
