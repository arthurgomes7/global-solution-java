# Orbit Bridge

API REST em Spring Boot para gerenciar **Organizações** e **Soluções Espaciais** ligadas a prioridades, status e ODS.  
O projeto está organizado em camadas, com foco em separação de responsabilidades e persistência em banco MySQL.

## Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Security
- MySQL
- Lombok
- Maven

## Estrutura do projeto

O código segue uma organização em camadas:

- `model`: entidades JPA e enums de domínio.
- `dto`: objetos de entrada usados nas requisições.
- `repository`: acesso ao banco com Spring Data JPA.
- `service`: regras de negócio e coordenação das operações.
- `controller`: exposição dos endpoints REST.
- `exceptions`: exceções personalizadas do domínio.

## Entidades e DTOs

As entidades principais são:

- `Organizacao`: representa a organização responsável pelas soluções.
- `SolucaoEspacial`: representa a solução cadastrada, com nome, descrição, área de atuação, status, prioridade, ODS e organização responsável.

Os enums complementam o domínio:

- `StatusSolucao`: `EM_ANALISE`, `EM_DESENVOLVIMENTO`, `IMPLEMENTADA`, `PAUSADA`, `INATIVA`.
- `Prioridade`: `BAIXA`, `MEDIA`, `ALTA`.
- `Ods`: enum criado para representar ODS específicas do projeto.

Os DTOs `OrganizacaoDto` e `SolucaoDto` são usados para receber dados da API com validação, evitando expor diretamente a entidade em toda entrada da aplicação.

## Repositories

Os repositórios usam `JpaRepository` para simplificar CRUD e consultas.

- `OrganizacaoRepository` faz persistência de organizações.
- `SolucaoRepository` faz persistência de soluções e adiciona consultas por área, ODS e prioridade.

Essa camada concentra o acesso ao banco e evita SQL manual na maior parte do fluxo.

## Services

A camada de service contém a regra de negócio principal.

### `OrganizacaoService`

- cadastra organização;
- lista organizações;
- exclui organização por id.

### `SolucaoService`

- cadastra solução;
- lista soluções;
- busca por id, ODS e área;
- atualiza solução;
- altera status;
- remove solução por soft delete;
- gera um resumo agregado das soluções.

As funções retornam, em geral, `ResponseEntity`, `List`, `Optional` ou `String`, dependendo do tipo de operação.  
Os retornos HTTP mais comuns são:

- `200 OK` para sucesso;
- `204 No Content` para exclusões ou atualizações sem corpo;
- `404 Not Found` quando o recurso não existe;
- `400 Bad Request` para operações inválidas.

Regras de negócio já presentes no service:

- solução inexistente retorna erro adequado;
- solução removida é marcada como `INATIVA` em vez de exclusão física;
- status de solução `INATIVA` ou `PAUSADA` não pode ser alterado;
- o resumo agrupa quantidades por status, área e prioridade.

## Controllers

Os controllers expõem a API REST e recebem os dados enviados pelo cliente.

- `OrganizacaoController`: expõe endpoints para listar, cadastrar e excluir organizações.
- `SolucaoController`: expõe endpoints para listar, cadastrar, atualizar, consultar e gerar resumo de soluções.

Essas classes não concentram regra de negócio; elas validam a entrada, chamam o service e devolvem a resposta HTTP.

## Exception personalizada

Existe uma exceção própria chamada `SolucaoException`, criada para padronizar erros do domínio e permitir tratamento mais claro no futuro.

Isso ajuda a separar erros de negócio de erros técnicos e abre espaço para um handler global de exceções.

## Banco de dados

O projeto está configurado para MySQL local:

- banco: `global_java`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.show-sql=true`

O uso de `ddl-auto=update` facilita desenvolvimento porque o Hibernate ajusta o schema automaticamente.  


## Exemplos de uso do `SolucaoController`

Listar soluções:

```bash
GET /solucoes
```

Buscar solução por id:

```bash
GET /solucoes/1
```

Gerar resumo:

```bash
GET /solucoes/resumo
```

Cadastrar solução:

```bash
POST /solucoes/cadastrar
Content-Type: application/json

{
  "nome": "Plataforma Orbital",
  "descricao": "Sistema para monitoramento de dados espaciais",
  "areaAtuacao": "Monitoramento",
  "status": "EM_ANALISE",
  "prioridade": "ALTA",
  "ods": [9, 13],
  "idOrganizacaoResponsavel": {
    "id": 1
  }
}
```

Atualizar solução:

```bash
PUT /solucoes?id=1
Content-Type: application/json

{
  "nome": "Plataforma Orbital v2",
  "descricao": "Versão atualizada da solução",
  "areaAtuacao": "Monitoramento",
  "status": "EM_DESENVOLVIMENTO",
  "prioridade": "ALTA",
  "ods": [9, 11],
  "idOrganizacaoResponsavel": {
    "id": 1
  }
}
```

Excluir solução:

```bash
DELETE /solucoes/1?id=1
```

> Observação: as rotas de busca por ODS e área existem no controller atual, mas a definição dos mappings merece revisão para evitar conflitos de path.
