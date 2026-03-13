package com.ecommerce.productos.repository;

import com.ecommerce.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Producto.
 * Spring Data JPA genera automáticamente las consultas CRUD.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByDisponibleTrue();

    boolean existsByNombre(String nombre);
}
