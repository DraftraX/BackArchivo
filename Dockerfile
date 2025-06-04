# Usar JDK
FROM eclipse-temurin:17-jdk

# Puerto
EXPOSE 8080

# Directorio de trabajo
WORKDIR /root

# Copiar archivos de configuración
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# Dar permisos
RUN chmod +x /root/mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY ./src /root/src

# Construir la app
RUN ./mvnw clean install -DskipTests

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "/root/target/archivo-0.0.1-SNAPSHOT.jar"]
