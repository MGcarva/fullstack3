# Microservicio de Productos — E-Commerce (Spring Boot)

## Actividad 1.1.2 — Diseño e Implementación Inicial de Microservicios

**Asignatura:** DSY1106 — Desarrollo Fullstack III

---

## 1. Descripción

Este proyecto es la versión **Java + Spring Boot** del microservicio de productos del e-commerce.
Replica exactamente la misma funcionalidad del proyecto Django (Fullstack3), demostrando
la **interoperabilidad** y **libertad tecnológica** que ofrecen los microservicios.

## 2. Tecnologías Utilizadas

| Tecnología           | Versión  | Propósito                              |
|----------------------|----------|----------------------------------------|
| Java                 | 17       | Lenguaje de programación               |
| Spring Boot          | 3.2.3    | Framework principal                    |
| Spring Data JPA      | —        | ORM / acceso a datos                   |
| H2 Database          | —        | Base de datos (archivo, similar a SQLite)|
| Lombok               | —        | Reducir código boilerplate             |
| Jakarta Validation   | —        | Validación de datos de entrada         |
| SpringDoc OpenAPI    | 2.3.0    | Documentación Swagger UI               |

## 3. Estructura del Proyecto

```
Fullstack3-SpringBoot/
├── pom.xml                          # Dependencias Maven (equivalente a requirements.txt)
├── README.md
└── src/
    └── main/
        ├── java/com/ecommerce/productos/
        │   ├── ProductosApplication.java    # Clase principal (equivalente a manage.py)
        │   ├── config/
        │   │   ├── DataSeeder.java          # Datos de ejemplo (equivalente a seed_data.py)
        │   │   └── GlobalExceptionHandler.java
        │   ├── controller/
        │   │   ├── ProductoController.java  # Endpoints REST (equivalente a views.py)
        │   │   ├── CategoriaController.java
        │   │   ├── HealthController.java
        │   │   ├── UsuarioController.java
        │   │   ├── InventarioController.java
        │   │   └── PedidoController.java
        │   ├── dto/
        │   │   ├── ProductoDTO.java         # Respuesta (equivalente a serializers.py)
        │   │   ├── ProductoCreateDTO.java
        │   │   ├── CategoriaDTO.java
        │   │   ├── UsuarioDTO.java (y UsuarioCreateDTO.java)
        │   │   ├── InventarioDTO.java
        │   │   └── PedidoDTO.java (y PedidoCreateDTO.java)
        │   ├── model/
        │   │   ├── Producto.java            # Entidad JPA (equivalente a models.py)
        │   │   ├── Categoria.java
        │   │   ├── Usuario.java
        │   │   ├── Inventario.java
        │   │   └── Pedido.java
        │   ├── repository/
        │   │   ├── ProductoRepository.java  # Acceso a datos (Spring Data)
        │   │   ├── CategoriaRepository.java
        │   │   ├── UsuarioRepository.java
        │   │   ├── InventarioRepository.java
        │   │   └── PedidoRepository.java
        │   └── service/
        │       ├── ProductoService.java     # Lógica de negocio
        │       ├── CategoriaService.java
        │       ├── UsuarioService.java
        │       ├── InventarioService.java
        │       └── PedidoService.java
        └── resources/
            └── application.properties       # Configuración (equivalente a settings.py)
```

## 4. Endpoints de la API

| Método | Endpoint              | Descripción                     |
|--------|-----------------------|---------------------------------|
| GET    | `/api/productos`      | Lista todos los productos       |
| POST   | `/api/productos`      | Crea un nuevo producto          |
| GET    | `/api/productos/{id}` | Detalle de un producto          |
| PUT    | `/api/productos/{id}` | Actualiza un producto           |
| DELETE | `/api/productos/{id}` | Elimina un producto             |
| GET    | `/api/categorias`     | Lista todas las categorías      |
| POST   | `/api/categorias`     | Crea una nueva categoría        |
| GET    | `/api/health`         | Estado del microservicio        |
| GET    | `/api/usuarios`       | Lista todos los usuarios        |
| POST   | `/api/usuarios`       | Crea un nuevo usuario           |
| GET    | `/api/usuarios/{id}`  | Detalle de un usuario           |
| DELETE | `/api/usuarios/{id}`  | Elimina un usuario              |
| GET    | `/api/inventarios`    | Lista todo el inventario        |
| PUT    | `/api/inventarios/producto/{id}`| Actualiza stock de un producto |
| GET    | `/api/pedidos`        | Lista todos los pedidos         |
| POST   | `/api/pedidos`        | Crea un nuevo pedido            |

## 5. Cómo Ejecutar

### Prerrequisitos
- Java 17 o superior
- Maven 3.8+ (o usar el wrapper incluido)

### Pasos

```bash
# 1. Navegar al proyecto
cd Fullstack3-SpringBoot

# 2. Compilar y ejecutar
mvn spring-boot:run

# 3. La API estará disponible en:
#    http://localhost:8000/api/productos
#    http://localhost:8000/api/categorias
#    http://localhost:8000/api/health
#    http://localhost:8000/api/usuarios
#    http://localhost:8000/api/inventarios
#    http://localhost:8000/api/pedidos

# 4. Swagger UI (documentación interactiva):
#    http://localhost:8000/api/swagger-ui.html

# 5. Consola H2 (base de datos):
#    http://localhost:8000/h2-console
#    JDBC URL: jdbc:h2:file:./data/ecommerce_db
```

## 6. Ejemplo de Uso

### Listar productos
```bash
curl http://localhost:8000/api/productos
```

### Crear un producto
```bash
curl -X POST http://localhost:8000/api/productos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Teclado Mecánico RGB",
    "descripcion": "Teclado mecánico con switches Cherry MX",
    "precio": 59990,
    "stock": 30,
    "categoriaId": 1,
    "disponible": true,
    "imagenUrl": "https://ejemplo.com/teclado.jpg"
  }'
```
### Crear un usuario
```bash
curl -X POST http://localhost:8000/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Ana López",
    "email": "ana.lopez@ejemplo.com",
    "direccion": "Avenida Siempre Viva 742",
    "telefono": "+56911223344"
  }'
```
### Actualizar o inicializar stock (Inventario)
```bash
curl -X PUT "http://localhost:8000/api/inventarios/producto/1?cantidad=150"
```
### Crear un pedido
```bash
curl -X POST http://localhost:8000/api/pedidos \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "total": 45990.0
  }'
```
## 7. Comparativa Django vs Spring Boot

| Concepto               | Django (Python)              | Spring Boot (Java)                |
|------------------------|------------------------------|-----------------------------------|
| Modelos / Entidades    | `models.py`                  | `model/*.java` + JPA annotations  |
| Serialización          | `serializers.py` (DRF)       | `dto/*.java` + Jackson            |
| Vistas / Controladores | `views.py`                   | `controller/*.java`               |
| Rutas / URLs           | `urls.py`                    | `@RequestMapping` annotations     |
| ORM                    | Django ORM                   | Hibernate / Spring Data JPA       |
| Base de datos          | SQLite (`db.sqlite3`)        | H2 (`data/ecommerce_db`)          |
| Validaciones           | Serializer validators        | Jakarta Validation annotations    |
| Datos iniciales        | `seed_data.py`               | `DataSeeder.java` (CommandLineRunner)|
| Configuración          | `settings.py`                | `application.properties`          |
| Dependencias           | `requirements.txt` + pip     | `pom.xml` + Maven                 |
## 8. Despliegue con Docker

Este microservicio está preparado para ser contenerizado utilizando Docker, lo que garantiza su portabilidad, escalabilidad y un despliegue eficiente en entornos productivos.

### Configuración del Dockerfile (Multi-stage Build)
Se implementó un archivo `Dockerfile` utilizando una estrategia de **múltiples etapas** (multi-stage build) para optimizar el peso y la seguridad de la imagen final:
1. **Etapa de Construcción (Build):** Utiliza una imagen oficial de Maven con JDK 17. Se encarga de descargar las dependencias y compilar el código fuente, generando el archivo ejecutable `.jar` sin necesidad de tener Maven instalado localmente.
2. **Etapa de Ejecución (Run):** Utiliza una imagen base ultraligera de Java 17 (`eclipse-temurin:17-jre-alpine`). Recupera exclusivamente el `.jar` compilado de la etapa anterior, desechando el código fuente y las herramientas de construcción para crear un contenedor altamente eficiente.

Adicionalmente, el archivo `application.properties` fue adaptado para inyectar configuraciones mediante **variables de entorno** (por ejemplo, `server.port=${SERVER_PORT:8000}`). Esto permite modificar puertos y credenciales de bases de datos de forma dinámica al momento de correr el contenedor, sin alterar el código fuente.

### Comandos de Ejecución

**1. Construir la imagen:**
Ubicado en la raíz del proyecto (donde se encuentra el `Dockerfile`), ejecuta el siguiente comando para crear la imagen de Docker:
```bash
docker build -t mi-api-productos .
```
**2. Ejecutar el contenedor:**
Una vez construida la imagen, levanta el contenedor en segundo plano (-d), mapeando el puerto 8000 hacia tu máquina local y asignándole un nombre identificable:

```bash
docker run -d -p 8000:8000 --name mi-contenedor-api mi-api-productos
```
(Nota: Es posible inyectar variables de entorno en este paso agregando flags, por ejemplo: -e SERVER_PORT=9090 -e DB_USER=admin).

**3. Verificar la ejecución:**
Para comprobar que el contenedor está activo y funcionando correctamente, utiliza el comando:

```Bash
docker ps
```
Una vez levantado, la API estará lista para recibir peticiones a través de http://localhost:8000/api/productos.