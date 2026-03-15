package com.ecommerce.productos.service;

import com.ecommerce.productos.dto.UsuarioCreateDTO;
import com.ecommerce.productos.dto.UsuarioDTO;
import com.ecommerce.productos.model.Usuario;
import com.ecommerce.productos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertToDTO(usuario);
    }

    @Transactional
    public UsuarioDTO save(UsuarioCreateDTO createDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(createDTO.getNombre());
        usuario.setEmail(createDTO.getEmail());
        usuario.setDireccion(createDTO.getDireccion());
        usuario.setTelefono(createDTO.getTelefono());
        
        Usuario saved = usuarioRepository.save(usuario);
        return convertToDTO(saved);
    }

    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Método auxiliar para convertir Entidad a DTO
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setDireccion(usuario.getDireccion());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }
}