# Sistema de Pagamentos - Hamburgueria

## Descrição do Projeto

Este projeto consiste em uma API REST desenvolvida com Spring Boot para gerenciamento de pagamentos de uma hamburgueria. O sistema permite o cadastro de clientes, categorias de pagamento e processamento de pagamentos utilizando diferentes estratégias de pagamento.

O projeto foi desenvolvido como atividade prática da disciplina PPGTI 1004 - Desenvolvimento Web II, tendo como foco principal a utilização de persistência híbrida com múltiplas bases de dados e relacionamentos avançados utilizando JPA.

---

# Objetivo do Domínio

O domínio escolhido foi um sistema de pagamentos para hamburgueria por permitir a modelagem de relacionamentos complexos entre entidades, além da aplicação de regras de negócio relacionadas ao processamento de pagamentos.

O sistema implementa:

* gerenciamento de clientes
* gerenciamento de categorias de pagamento
* processamento de pagamentos
* auditoria de operações em base separada
* múltiplos tipos de pagamento utilizando Strategy Pattern

---

# Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Lombok
* Maven
* Swagger/OpenAPI
* Postman

---

# Arquitetura do Projeto

O projeto foi organizado utilizando arquitetura em camadas:

* Controller
* Service
* Repository
* DTO
* Model/Entity
* Config

---

# Estrutura de Persistência

A aplicação utiliza duas bases de dados distintas.

## Base Principal

Responsável por armazenar os dados principais do domínio:

* clientes
* pagamentos
* categorias de pagamento

## Base de Auditoria

Responsável pelo armazenamento dos logs de auditoria das operações realizadas no sistema.

Toda operação de criação, atualização ou remoção de pagamentos gera automaticamente um registro de auditoria.

---

# Configuração de Múltiplas Bases

O projeto possui duas configurações distintas de datasource:

* `PrimaryDatabaseConfig`
* `AuditDatabaseConfig`

Cada base possui:

* datasource próprio
* transaction manager próprio
* entity manager próprio

---

# Modelagem das Entidades

## Cliente

Representa os clientes do sistema.

### Relacionamentos

* Um cliente pode possuir vários pagamentos.

Relacionamento:

* One-to-Many

---

## Pagamento

Representa os pagamentos realizados no sistema.

### Relacionamentos

* Muitos pagamentos podem pertencer a um cliente.
* Um pagamento pode possuir várias categorias.

Relacionamentos:

* Many-to-One
* Many-to-Many

---

## CategoriaPagamento

Representa categorias associadas aos pagamentos.

Exemplos:

* DELIVERY
* ASSINATURA

Relacionamento:

* Many-to-Many

---

# Relacionamentos Implementados

## One-to-Many / Many-to-One

Cliente -> Pagamento

```txt
Um cliente pode possuir vários pagamentos.
Um pagamento pertence a apenas um cliente.
```

---

## Many-to-Many

Pagamento -> CategoriaPagamento

```txt
Um pagamento pode possuir várias categorias.
Uma categoria pode estar associada a vários pagamentos.
```

---

# Uso de Cascade

Foi utilizada configuração de cascade para garantir integridade nas operações entre entidades relacionadas.

Exemplo:

```java
@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
```

e

```java
@ManyToMany(cascade = CascadeType.PERSIST)
```

---

# Validação de Dados

A API realiza validação dos dados recebidos utilizando DTOs e validações de negócio.

Exemplos:

* validação de valor positivo para pagamentos
* validação de cliente existente
* validação de categorias existentes

---

# DTOs

Foram utilizados DTOs para evitar exposição direta das entidades do banco de dados.

DTOs utilizados:

* PagamentoRequestDTO
* PagamentoResponseDTO
* AuditLogResponseDTO

---

# Processamento de Pagamentos

O sistema utiliza Strategy Pattern para processar diferentes tipos de pagamento.

## Estratégias Implementadas

### PIX

Pagamentos via PIX são automaticamente aprovados.

### CARTAO

Pagamentos acima de 1000 são recusados.

---

# Factory Pattern

Foi utilizada uma factory para selecionar dinamicamente a estratégia correta de processamento de pagamento.

Classe:

```txt
PagamentoStrategyFactory
```

---

# Auditoria

Toda operação relevante de pagamento gera automaticamente um log na base de auditoria.

Exemplos de ações registradas:

* CRIACAO_PAGAMENTO
* ATUALIZACAO_PAGAMENTO
* DELECAO_PAGAMENTO

---

# Requisitos de Consultas

O projeto implementa:

## Consulta JPQL

```java
@Query("SELECT p FROM Pagamento p WHERE p.valor > :valor")
```

---

## Consulta SQL Nativa

```java
@Query(value = "SELECT * FROM pagamento WHERE tipo = :tipo", nativeQuery = true)
```

---

## JOIN FETCH

```java
@Query("SELECT p FROM Pagamento p JOIN FETCH p.cliente WHERE p.id = :id")
```

Utilizado para controle explícito do carregamento de relacionamentos.

---

# Endpoints Principais

## Clientes

* POST `/clientes`
* GET `/clientes`

---

## Categorias

* POST `/categorias`
* GET `/categorias`

---

## Pagamentos

* POST `/pagamentos`
* PUT `/pagamentos/{id}`
* GET `/pagamentos`
* GET `/pagamentos/{id}`
* GET `/pagamentos/tipo/{tipo}`
* DELETE `/pagamentos/{id}`

---

## Auditoria

* GET `/auditoria`

---

# Swagger

Documentação disponível em:

```txt
http://localhost:8080/swagger-ui/index.html
```

---

# Como Executar

## Compilar o projeto

```bash
mvn clean install
```

---

## Executar a aplicação

```bash
mvn spring-boot:run
```

---

# Testes

Os testes da API podem ser realizados utilizando a collection do Postman incluída no projeto.

---

# Estrutura do Projeto

```txt
src
 ├── controller
 ├── service
 ├── repository
 ├── dto
 ├── model
 ├── strategy
 ├── config
```

---

# Autor

Allysson Freitas
