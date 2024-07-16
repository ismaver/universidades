# Usar una imagen base de OpenJDK
FROM openjdk:11-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Compilar la aplicación
RUN ./mvnw package -DskipTests

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/universities-service-1.0.0.jar"]
