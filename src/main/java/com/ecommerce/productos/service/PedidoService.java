package com.ecommerce.productos.service;

import com.ecommerce.productos.dto.PedidoCreateDTO;
import com.ecommerce.productos.dto.PedidoDTO;
import com.ecommerce.productos.model.Pedido;
import com.ecommerce.productos.model.Usuario;
import com.ecommerce.productos.repository.PedidoRepository;
import com.ecommerce.productos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return convertToDTO(pedido);
    }

    @Transactional
    public PedidoDTO save(PedidoCreateDTO createDTO) {
        Usuario usuario = usuarioRepository.findById(createDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(createDTO.getTotal());
        // El estado y la fecha se asignan por defecto en la entidad
        
        Pedido saved = pedidoRepository.save(pedido);
        return convertToDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setUsuarioId(pedido.getUsuario().getId());
        dto.setNombreUsuario(pedido.getUsuario().getNombre());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());
        return dto;
    }
}