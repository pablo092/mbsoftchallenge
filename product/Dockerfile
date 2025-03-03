# Usa una imagen de JDK 17 como base
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /product
# Copia el código fuente
COPY . .
# ✅ Agregar permisos de ejecución
# Dar permisos de ejecución al wrapper de Maven y asegurarse de que existen los archivos
RUN chmod +x mvnw \
    && chmod +x .mvn/wrapper/maven-wrapper.jar \
    && ls -la .mvn/wrapper
# Compila la aplicación
RUN ./mvnw clean package -DskipTests

# Segunda etapa para reducir la imagen
FROM eclipse-temurin:17-jre-alpine
WORKDIR /product
COPY --from=builder /product/target/*.jar app.jar
# Puerto expuesto
EXPOSE 8081
# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
