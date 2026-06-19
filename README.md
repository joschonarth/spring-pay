<div align="center">

# 💸 Spring Pay

*A modern REST API for digital wallets and money transfers between users with Spring Boot.*

<img src="https://img.shields.io/github/last-commit/joschonarth/spring-pay?style=default&logo=git&logoColor=white&color=6DB33F&labelColor=27272a" alt="last-commit">
<img src="https://img.shields.io/github/languages/top/joschonarth/spring-pay?style=default&color=6DB33F&labelColor=27272a" alt="repo-top-language">
<img src="https://img.shields.io/github/languages/count/joschonarth/spring-pay?style=default&color=6DB33F&labelColor=27272a" alt="repo-language-count">

---

📃 [About](#-about)&nbsp;&nbsp;•&nbsp;&nbsp;
🛠️ [Technologies](#️-technologies)&nbsp;&nbsp;•&nbsp;&nbsp;
✨ [Features](#-features)&nbsp;&nbsp;•&nbsp;&nbsp;
📋 [Business Rules](#-business-rules)&nbsp;&nbsp;•&nbsp;&nbsp;
🚀 [Getting Started](#-getting-started)&nbsp;&nbsp;•&nbsp;&nbsp;
📖 [API Documentation](#-api-documentation)

</div>

---

## 📃 About

**Spring Pay** is a REST API for digital payments built with **Spring Boot**. It allows users to deposit funds and perform money transfers between accounts. The system supports two types of users — **common users** and **merchants** — each with their own wallet. Transfers are validated by an **external authorizer** and recipients are notified asynchronously via a **notification service**.

---

## 🛠️ Technologies

- ☕ **[Java 17](https://www.oracle.com/java/)** — Main programming language.
- 🌱 **[Spring Boot](https://spring.io/projects/spring-boot)** — Framework for building modern Java applications.
- 🗃️ **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** — Data access abstraction with Hibernate.
- 🌐 **[OpenFeign](https://spring.io/projects/spring-cloud-openfeign)** — Declarative HTTP client for external service communication.
- 📦 **[Maven](https://maven.apache.org/)** — Dependency management and project build automation.
- 🐬 **[MySQL](https://www.mysql.com/)** — Relational database used in production.
- 🐳 **[Docker](https://www.docker.com/)** — Database containerization for a reproducible environment.
- ✅ **[Bean Validation](https://beanvalidation.org/)** — Input data validation.
- 🏗️ **[Lombok](https://projectlombok.org/)** — Boilerplate reduction through code generation.
- 📝 **[SLF4J](https://www.slf4j.org/)** — Logging facade for application observability.

---

## ✨ Features

- [x] 👛 Wallet registration for common users and merchants
- [x] 💰 Balance deposit into wallets
- [x] 🔄 Money transfers between users
- [x] 🚫 Transfer restriction for merchant wallets (merchants cannot send transfers)
- [x] 💳 Balance validation before transfers
- [x] 🔐 External authorizer consultation before executing transfers
- [x] 📣 Asynchronous transfer notification to the recipient
- [x] ⚠️ Structured error handling with `ProblemDetails` and `RestExceptionHandler`
- [x] 📝 Application logging with SLF4J

---

## 📋 Business Rules

### Wallet Types

| Type | Can Send | Can Receive |
|---|---|---|
| 👤 Common User | ✅ Yes | ✅ Yes |
| 🏪 Merchant | ❌ No | ✅ Yes |

### Transfer Flow

```
1. Validate payer wallet type (only common users can transfer)
2. Validate payer balance (must have sufficient funds)
3. Consult external authorizer
4. Execute the transfer
5. Notify the recipient (asynchronously via CompletableFuture)
```

---

## 🚀 Getting Started

### 📋 Prerequisites

- ☕ [Java 17+](https://www.oracle.com/java/)
- 📦 [Maven](https://maven.apache.org/)
- 🐳 [Docker](https://www.docker.com/)

### 🔧 Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/joschonarth/spring-pay.git
    ```

2. Navigate to the project folder:

    ```bash
    cd spring-pay
    ```

### 🐳 Database

Start the MySQL container with Docker:

```bash
cd docker
docker compose up -d
```

### ▶️ Running

Start the application with Maven:

```bash
./mvnw spring-boot:run
```

The API will be available at **[http://localhost:8080](http://localhost:8080)**.

---

## 📖 API Documentation

### `POST /wallets`

Registers a new wallet for a user or merchant.

**Request Body:**

```json
{
    "name": "John Doe",
    "cpfCnpj": "123.456.789-00",
    "email": "john@email.com",
    "password": "secret123",
    "type": 1
}
```

**Fields:**

| Field | Type | Description |
|---|---|---|
| `name` | `string` | Full name of the user or merchant |
| `cpfCnpj` | `string` | CPF (common user) or CNPJ (merchant) |
| `email` | `string` | User e-mail address |
| `password` | `string` | Account password |
| `type` | `integer` | Wallet type: `1` = Common, `2` = Merchant |

---

### `POST /transfer`

Performs a money transfer between two wallets.

**Request Body:**

```json
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}
```

**Fields:**

| Field | Type | Description |
|---|---|---|
| `value` | `number` | Amount to be transferred |
| `payer` | `integer` | ID of the wallet sending the transfer |
| `payee` | `integer` | ID of the wallet receiving the transfer |

---

## ⭐ Support this Project

If you liked this project, leave a ⭐ on GitHub — it means a lot!

---

<div align="center">

Made with ♥ by **[João Otávio Schonarth](https://github.com/joschonarth)**

[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/joschonarth)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/joschonarth)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:joschonarth@gmail.com)

</div>
