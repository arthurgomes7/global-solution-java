# Orbit Bridge

## Nome da solucao

**Orbit Bridge**

## Problema escolhido

Organizacoes que desenvolvem solucoes voltadas ao setor espacial costumam armazenar e acompanhar suas iniciativas de forma descentralizada. Isso dificulta a visualizacao dos projetos existentes, o controle do status de desenvolvimento, a identificacao das areas de impacto e a relacao dessas solucoes com objetivos estrategicos e sustentaveis.

O **Orbit Bridge** resolve esse problema por meio de uma API centralizada para gerenciamento de solucoes espaciais. A plataforma permite cadastrar, consultar, atualizar e acompanhar solucoes desenvolvidas por diferentes organizacoes, reunindo informacoes como area de atuacao, prioridade, status e ODS relacionados. Com isso, o sistema facilita a gestao das iniciativas e oferece uma visao consolidada do ecossistema de solucoes espaciais cadastradas.

## ODS relacionado

O projeto esta relacionado principalmente a **ODS 9 - Industria, Inovacao e Infraestrutura**, pois apoia a organizacao, o acompanhamento e a visibilidade de solucoes inovadoras no setor espacial.

Tambem pode contribuir indiretamente com outros ODS, dependendo das solucoes cadastradas na plataforma, como iniciativas ligadas a sustentabilidade, monitoramento ambiental, cidades inteligentes ou resposta a desastres.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- MySQL
- Lombok
- Maven

## Como executar o projeto

### Pre-requisitos

- Java 21 instalado
- MySQL instalado e em execucao
- Git instalado

### Configuracao do banco de dados

Crie um banco de dados MySQL com o nome:

```sql
CREATE DATABASE global_java;
```

O projeto esta configurado em `src/main/resources/application.properties` com os seguintes dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/global_java
spring.datasource.username=${login_DB}
spring.datasource.password=${senha_DB}
```

### Execucao

No terminal, dentro da pasta do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows, tambem e possivel executar:

```bash
mvnw.cmd spring-boot:run
```

A API sera iniciada em:

```text
http://localhost:8080
```

## Principais endpoints

### Organizacoes

- `GET /organizacoes` - lista as organizacoes cadastradas
- `POST /organizacoes/cadastrar` - cadastra uma organizacao
- `DELETE /organizacoes/deletar/{id}` - remove uma organizacao

### Solucoes espaciais

- `GET /solucoes` - lista as solucoes cadastradas
- `GET /solucoes/id/{id}` - busca uma solucao por ID
- `GET /solucoes/ods/{ods}` - busca solucoes por ODS
- `GET /solucoes/area/{area}` - busca solucoes por area de atuacao
- `POST /solucoes/cadastrar` - cadastra uma nova solucao
- `PUT /solucoes/{id}` - atualiza uma solucao
- `PATCH /solucoes/status/{id}?status={status}` - altera o status de uma solucao
- `DELETE /solucoes/deletar/{id}` - remove logicamente uma solucao
- `GET /solucoes/resumo` - gera um resumo das solucoes cadastradas

## Integrantes do grupo

- Arthur Gomes - RM 560771
- Luiz Silva - RM 560110
- Matheus Siroma - RM 560248
- Pedro Estevam - RM 560642
- Witalon Antonio - RM 559023

## Link do video ou caminho
https://youtu.be/4v4glofOPC4
