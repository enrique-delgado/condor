# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Argumento para copiar el JAR generado por Maven
ARG JAR_FILE=target/condor-transactions-manager-*.jar

# Copiar el JAR al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto de la aplicación
EXPOSE 2000

# Comando de arranque
ENTRYPOINT ["java","-jar","/app/app.jar"]
