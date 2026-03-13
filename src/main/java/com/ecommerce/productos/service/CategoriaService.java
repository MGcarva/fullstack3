package com.ecommerce.productos.service;

import com.ecommerce.productos.dto.CategoriaDTO;
import com.ecommerce.productos.model.Categoria;
import com.ecommerce.productos.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para lógica de negocio de Categorías.
 */
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obtenerPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
        return toDTO(categoria);
    }

    public CategoriaDTO crear(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
        Categoria guardada = categoriaRepository.save(categoria);
        return toDTO(guardada);
    }

    // ── Mapper ──
    private CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
