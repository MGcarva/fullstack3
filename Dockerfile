# --- ETAPA 1: El Chef (Compilación) ---
# Usamos una imagen de Docker que ya tiene Maven instalado
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
# Copiamos tus archivos a la máquina de Docker
COPY pom.xml .
COPY src ./src
# ¡Docker ejecuta el comando por ti!
RUN mvn clean package -DskipTests

# --- ETAPA 2: El Plato Limpio (Ejecución) ---
# Usamos un Java ultra ligero solo para correr el programa
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Tomamos el .jar que el chef creó en la Etapa 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]