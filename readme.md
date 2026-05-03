# Sistema de Pagamentos - Hamburgueria


API REST desenvolvida com Spring Boot para gerenciamento de clientes, categorias e pagamentos de uma hamburgueria, utilizando autenticação JWT, controle de acesso baseado em roles e arquitetura desacoplada com DTOs.

---

# Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Bean Validation
* PostgreSQL / H2
* Swagger OpenAPI

---

# Arquitetura da Aplicação

A aplicação segue arquitetura REST com separação de responsabilidades:

* Controllers → exposição dos endpoints REST
* Services → regras de negócio
* Repositories → acesso ao banco de dados
* DTOs → comunicação da API
* Entities → representação das tabelas do banco

A aplicação utiliza DTOs para desacoplar totalmente as entidades do banco da interface da API.

---

# Segurança da Aplicação

A autenticação é realizada utilizando JWT Token.

O controle de acesso é feito utilizando:

```java
@PreAuthorize(...)
```

com regras baseadas em roles.

Roles disponíveis:

* ADMIN
* MANAGER
* ATTENDANT

---

# Matriz de Permissões

| Endpoint                | Método | ADMIN | MANAGER | ATTENDANT | Público |
| ----------------------- | ------ | ----- | ------- | --------- | ------- |
| /info                   | GET    | ✅     | ✅       | ✅         | ✅       |
| /auth/login             | POST   | ✅     | ✅       | ✅         | ✅       |
| /clientes               | GET    | ✅     | ✅       | ✅         | ❌       |
| /clientes               | POST   | ✅     | ✅       | ❌         | ❌       |
| /clientes/{id}          | GET    | ✅     | ✅       | ✅         | ❌       |
| /clientes/{id}          | PUT    | ✅     | ✅       | ❌         | ❌       |
| /clientes/{id}          | DELETE | ✅     | ❌       | ❌         | ❌       |
| /categorias             | GET    | ✅     | ✅       | ✅         | ❌       |
| /categorias             | POST   | ✅     | ❌       | ❌         | ❌       |
| /categorias/{id}        | GET    | ✅     | ✅       | ✅         | ❌       |
| /categorias/{id}        | PUT    | ✅     | ✅       | ✅         | ❌       |
| /categorias/{id}        | DELETE | ✅     | ✅       | ❌         | ❌       |
| /pagamentos             | GET    | ✅     | ✅       | ✅         | ❌       |
| /pagamentos             | POST   | ✅     | ❌       | ❌         | ❌       |
| /pagamentos/{id}        | GET    | ✅     | ✅       | ✅         | ❌       |
| /pagamentos/{id}        | PUT    | ✅     | ✅       | ❌         | ❌       |
| /pagamentos/{id}        | DELETE | ✅     | ❌       | ❌         | ❌       |
| /pagamentos/tipo/{tipo} | GET    | ✅     | ✅       | ✅         | ❌       |
| /auditoria              | GET    | ✅     | ❌       | ❌         | ❌       |

---

# Autenticação

## Login

Endpoint:

```http
POST /auth/login
```

### Request

```json
{
  "username": "admin",
  "password": "123456"
}
```

### Response

```json
{
  "token": "jwt_token_aqui"
}
```

---

# Exemplos de DTOs

## Desacoplamento entre Entidade e API

A aplicação não expõe diretamente as entidades do banco de dados.

As entidades representam a estrutura interna persistida no banco, enquanto os DTOs controlam os dados enviados e recebidos pela API.

---

# Exemplo: Cliente

## Entidade (Banco de Dados)

```java
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
```

A entidade possui atributos internos que não devem ser expostos diretamente.

---

## DTO de Entrada (Request)

Utilizado para criação e atualização de clientes.

### Exemplo Request

```json
{
  "nome": "Allysson",
  "email": "allysson@email.com"
}
```

---

## DTO de Saída (Response)

Utilizado para retorno da API.

### Exemplo Response

```json
{
  "id": 1,
  "nome": "Allysson",
  "email": "allysson@email.com"
}
```

Observe que informações sensíveis ou internas não são expostas.

---

# Validações

Os DTOs de entrada utilizam Bean Validation.

Exemplos utilizados:

```java
@NotBlank
@Email
@NotNull
@Size
```

Essas validações garantem integridade dos dados recebidos pela API.

---

# Status HTTP Utilizados

| Status           | Descrição                        |
| ---------------- | -------------------------------- |
| 200 OK           | Requisição realizada com sucesso |
| 201 Created      | Recurso criado com sucesso       |
| 204 No Content   | Recurso removido com sucesso     |
| 400 Bad Request  | Dados inválidos                  |
| 401 Unauthorized | Usuário não autenticado          |
| 403 Forbidden    | Usuário sem permissão            |
| 404 Not Found    | Recurso não encontrado           |

---

# Endpoints Principais

## Público

```http
GET /info
```

---

## Autenticação

```http
POST /auth/login
```

---

## Clientes

```http
GET /clientes
POST /clientes
GET /clientes/{id}
PUT /clientes/{id}
DELETE /clientes/{id}
```

---

## Categorias

```http
GET /categorias
POST /categorias
GET /categorias/{id}
PUT /categorias/{id}
DELETE /categorias/{id}
```

---

## Pagamentos

```http
GET /pagamentos
POST /pagamentos
GET /pagamentos/{id}
PUT /pagamentos/{id}
DELETE /pagamentos/{id}
GET /pagamentos/tipo/{tipo}
```

---

## Auditoria

```http
GET /auditoria
```

---

# Collection Postman

A aplicação possui collection Postman contendo todos os endpoints da API com autenticação JWT automatizada.

---

# Execução do Projeto

## Clonar repositório

```bash
git clone <https://github.com/allyssonmast/Desenvolvimento-Web-II-Atividade-03>
```

## Executar aplicação

```bash
./mvnw spring-boot:run
```

---

# Autor

Desenvolvido por Allysson Freitas.
