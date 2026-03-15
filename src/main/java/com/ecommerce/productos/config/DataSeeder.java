package com.ecommerce.productos.config;

import com.ecommerce.productos.model.Categoria;
import com.ecommerce.productos.model.Inventario;
import com.ecommerce.productos.model.Pedido;
import com.ecommerce.productos.model.Producto;
import com.ecommerce.productos.model.Usuario;
import com.ecommerce.productos.repository.CategoriaRepository;
import com.ecommerce.productos.repository.InventarioRepository;
import com.ecommerce.productos.repository.PedidoRepository;
import com.ecommerce.productos.repository.ProductoRepository;
import com.ecommerce.productos.repository.UsuarioRepository;

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
    private final UsuarioRepository usuarioRepository;
    private final InventarioRepository inventarioRepository;
    private final PedidoRepository pedidoRepository;
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
        // ── AGREGAR ESTO JUSTO DESPUÉS DE CREAR LOS PRODUCTOS ──

        log.info("Cargando nuevos datos (Usuarios, Inventarios, Pedidos)...");

        // 1. Crear usuarios de prueba
        Usuario usuario1 = crearUsuario("Juan Pérez", "juan@ejemplo.com", "Av. Libertador 123, Santiago", "+56912345678");
        Usuario usuario2 = crearUsuario("María Gómez", "maria@ejemplo.com", "Calle Falsa 456, Valparaíso", "+56987654321");

        // 2. Generar Inventario dinámico
        // Buscamos todos los productos que tu código anterior acaba de crear en la base de datos
        productoRepository.findAll().forEach(producto -> {
            // Por cada producto, le creamos un registro en la tabla de inventario
            // Usamos el mismo stock que le pusiste al producto originalmente para mantener consistencia
            crearInventario(producto.getId(), producto.getStock(), "Bodega Principal");
        });

        // 3. Crear Pedidos de prueba asociados a los usuarios
        // Simulamos que el usuario 1 compró algo de 150.000 y ya lo pagó
        crearPedido(usuario1, 150000.0, "PAGADO");
        // Simulamos que el usuario 2 tiene un pedido pendiente de 29.990
        crearPedido(usuario2, 29990.0, "PENDIENTE");
        log.info("===================================================");
        log.info("Total categorías: {}", categoriaRepository.count());
        log.info("Total productos:  {}", productoRepository.count());
        log.info("Total usuarios:    {}", usuarioRepository.count());
        log.info("Total inventarios: {}", inventarioRepository.count());
        log.info("Total pedidos:     {}", pedidoRepository.count());
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
    // --- AGREGAR ESTOS MÉTODOS AL FINAL DE LA CLASE ---

    /**
     * Crea y guarda un Usuario en la base de datos.
     * Instancia la entidad usando setters tradicionales.
     */
    private Usuario crearUsuario(String nombre, String email, String direccion, String telefono) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        
        Usuario guardado = usuarioRepository.save(usuario);
        log.info("  ✓ Usuario creado: {}", nombre);
        return guardado;
    }

    /**
     * Crea y guarda un registro de Inventario vinculado lógicamente a un Producto.
     * Usa LocalDateTime.now() para registrar el momento exacto de la creación.
     */
    private void crearInventario(Long productoId, Integer cantidad, String ubicacion) {
        Inventario inventario = new Inventario();
        inventario.setProductoId(productoId);
        inventario.setCantidadDisponible(cantidad);
        inventario.setUbicacion(ubicacion);
        inventario.setUltimaActualizacion(java.time.LocalDateTime.now());
        
        inventarioRepository.save(inventario);
        log.info("  ✓ Inventario creado para Producto ID: {} con {} unidades", productoId, cantidad);
    }

    /**
     * Crea y guarda un Pedido asignado a un Usuario específico.
     * Mapea el total y el estado de la compra.
     */
    private void crearPedido(Usuario usuario, Double total, String estado) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(total);
        pedido.setEstado(estado);
        pedido.setFechaPedido(java.time.LocalDateTime.now());
        
        pedidoRepository.save(pedido);
        log.info("  ✓ Pedido creado para {}: Total ${} - Estado: {}", usuario.getNombre(), total, estado);
    }
}
