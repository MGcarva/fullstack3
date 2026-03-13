package com.ecommerce.productos.config;

import com.ecommerce.productos.model.Categoria;
import com.ecommerce.productos.model.Producto;
import com.ecommerce.productos.repository.CategoriaRepository;
import com.ecommerce.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Cargador de datos de ejemplo.
 * Equivalente al seed_data.py del proyecto Django.
 * Se ejecuta automáticamente al iniciar la aplicación.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0) {
            log.info("Base de datos ya contiene datos. Omitiendo seed.");
            return;
        }

        log.info("Cargando datos de ejemplo...");

        // ── Crear categorías ──
        Categoria electronica = crearCategoria("Electrónica", "Dispositivos electrónicos y gadgets");
        Categoria ropa = crearCategoria("Ropa", "Vestimenta y accesorios de moda");
        Categoria hogar = crearCategoria("Hogar", "Artículos para el hogar y decoración");
        Categoria deportes = crearCategoria("Deportes", "Equipamiento y ropa deportiva");

        // ── Crear productos ──
        crearProducto("Smartphone Samsung Galaxy S24",
                "Teléfono inteligente de última generación con pantalla AMOLED",
                new BigDecimal("799990"), 50, electronica, true,
                "https://ejemplo.com/galaxy-s24.jpg");

        crearProducto("Notebook Lenovo ThinkPad",
                "Laptop profesional con procesador Intel i7 y 16GB RAM",
                new BigDecimal("1299990"), 25, electronica, true,
                "https://ejemplo.com/thinkpad.jpg");

        crearProducto("Audífonos Sony WH-1000XM5",
                "Audífonos inalámbricos con cancelación de ruido activa",
                new BigDecimal("349990"), 100, electronica, true,
                "https://ejemplo.com/sony-xm5.jpg");

        crearProducto("Polera Algodón Premium",
                "Polera de algodón 100% orgánico, disponible en varios colores",
                new BigDecimal("19990"), 200, ropa, true,
                "https://ejemplo.com/polera.jpg");

        crearProducto("Zapatillas Running Nike Air",
                "Zapatillas de running con tecnología Air Max para mayor comodidad",
                new BigDecimal("89990"), 75, deportes, true,
                "https://ejemplo.com/nike-air.jpg");

        crearProducto("Lámpara de Escritorio LED",
                "Lámpara LED regulable con puerto USB integrado",
                new BigDecimal("29990"), 150, hogar, true,
                "https://ejemplo.com/lampara.jpg");

        crearProducto("Balón de Fútbol Adidas",
                "Balón oficial de fútbol profesional",
                new BigDecimal("45990"), 0, deportes, false,
                "https://ejemplo.com/balon.jpg");

        log.info("===================================================");
        log.info("Total categorías: {}", categoriaRepository.count());
        log.info("Total productos:  {}", productoRepository.count());
        log.info("===================================================");
        log.info("¡Datos de ejemplo cargados exitosamente!");
    }

    private Categoria crearCategoria(String nombre, String descripcion) {
        Categoria cat = Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();
        Categoria guardada = categoriaRepository.save(cat);
        log.info("  ✓ Categoría creada: {}", nombre);
        return guardada;
    }

    private void crearProducto(String nombre, String descripcion, BigDecimal precio,
                                int stock, Categoria categoria, boolean disponible,
                                String imagenUrl) {
        Producto prod = Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .categoria(categoria)
                .disponible(disponible)
                .imagenUrl(imagenUrl)
                .build();
        productoRepository.save(prod);
        log.info("  ✓ Producto creado: {} - ${}", nombre, precio);
    }
}
