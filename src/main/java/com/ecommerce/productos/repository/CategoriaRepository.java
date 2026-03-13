package com.ecommerce.productos.repository;

import com.ecommerce.productos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Categoria.
 * Spring Data JPA genera automáticamente las consultas CRUD.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
