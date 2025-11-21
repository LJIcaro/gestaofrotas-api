# Estágio de Build
FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código fonte e constrói o projeto
COPY src ./src
RUN mvn package -DskipTests

# Estágio de Execução
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Copia o JAR construído do estágio de build
COPY --from=build /app/target/gestaofrotas-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
