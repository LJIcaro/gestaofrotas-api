# Sistema de Gestão de Frotas - API Backend

Esta é a API RESTful para o Sistema de Gestão de Frotas, desenvolvida com Spring Boot. Ela fornece todos os endpoints necessários para o gerenciamento de veículos, motoristas, agendamentos e outras operações relacionadas à frota.

## Sobre 

Este projeto foi desenvolvido como material avalitivo para a disciplina de Programação Orientada a Objetos em Java no Centro Universitário Ruy Barbosa, mistrada pelo Prof. Heleno Cardoso no periodo de 2025.2

### Integrantes do projeto

-Ícaro Lima de Jesus - 202308425951
-Ruan Santos Müller de Almeida - 202308425951

## Funcionalidades Principais

-   **Autenticação e Autorização:** Sistema seguro baseado em papéis (Administrador, Motorista) utilizando JSON Web Tokens (JWT).
-   **Gerenciamento de Veículos:** CRUD completo para veículos da frota, incluindo controle de status (Disponível, Em Manutenção, Inativo).
-   **Gerenciamento de Motoristas:** CRUD completo para motoristas, com integração à API externa ViaCEP para autopreenchimento de endereço.
-   **Sistema de Agendamentos:** Lógica completa para criar, visualizar, iniciar e finalizar viagens, com registro de histórico de status.
-   **Listagens com Filtros:** Endpoint para busca de agendamentos com filtros dinâmicos por período, motorista e status.
-   **Registros Adicionais:** Endpoints para registrar Manutenções, Abastecimentos e Ocorrências.

## Tecnologias Utilizadas

-   **Java 17+**
-   **Spring Boot**
-   **Spring Security** (para autenticação e autorização)
-   **Spring Data JPA** & **Hibernate** (para persistência de dados)
-   **H2 Database** (banco de dados em memória para desenvolvimento)
-   **Maven** (para gerenciamento de dependências e build)
-   **jjwt** (para manipulação de tokens JWT)

## Como Iniciar a Aplicação

### Pré-requisitos

- JDK 17+
- Maven (opcional se usar Docker)
- Docker e Docker Compose (se quiser rodar em contêiner)

### Rodar localmente

1. Configure o banco de dados PostgreSQL e atualize `src/main/resources/application.properties` ou defina as variáveis de ambiente:

   - SPRING_DATASOURCE_URL (ex.: jdbc:postgresql://localhost:5432/gestaofrotas)
   - SPRING_DATASOURCE_USERNAME
   - SPRING_DATASOURCE_PASSWORD

2. Na raiz do projeto execute:

```bash
./mvnw spring-boot:run
```

Ou para empacotar e executar o jar:

```bash
./mvnw clean package -DskipTests
java -jar target/*.jar
```

A aplicação ficará em: http://localhost:8080

### Rodar com Docker (recomendado)

O repositório inclui `docker-compose.yml` que sobe Postgres, pgAdmin e a aplicação.

Subir tudo:

```bash
docker-compose up --build
```

Subir em background:

```bash
docker-compose up -d --build
```

Parar:

```bash
docker-compose down
```

Portas padrão:

- App: 8080
- Postgres (host): 5423
- pgAdmin: 5050

Se preferir usar apenas Postgres via Docker e rodar a app localmente:

```bash
docker-compose up -d db
./mvnw spring-boot:run
```


### Credenciais de Teste

-   **Administrador:**
    -   **E-mail:** `admin@pitfrota.com`
    -   **Senha:** `admin123`
-   **Motoristas:**
    -   **E-mail:** `paulo.silva@email.com` (e outros criados no `DataLoader`)
    -   **Senha:** `senha123`
