# Usa una imagen de JDK 17 como base
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
# Copia el código fuente
COPY . .
# ✅ Agregar permisos de ejecución
RUN chmod +x mvnw
# RUN chmod +x .mvn/wrapper/maven-wrapper.jar
# Compila la aplicación
RUN ./mvnw clean package -DskipTests

# Segunda etapa para reducir la imagen
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
# Puerto expuesto
EXPOSE 8081
# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
