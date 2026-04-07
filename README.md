# 📖 OpenShelf

**OpenShelf** is a robust, Spring Boot 3.x backend for a modern Library Management System. It features a stateless **JWT Authentication** layer, role-based access control (RBAC), and a relational data model for managing books, authors, and members.

---

## 🚀 Features Implemented
* **Secure Authentication:** Stateless JWT-based login and registration system.
* **Role-Based Access (RBAC):** Implementation of `ADMIN` and `MEMBER` roles using Spring Security.
* **Book Management:** Full CRUD operations for library inventory, including ISBN tracking and genre classification.
* **Author Management:** Relational mapping to track authors and their biographical data.
* **Member Services:** Patron registration with BCrypt password hashing.
* **Global Exception Handling:** Centralized error handling returning standardized `ApiResponse<T>` objects.

---

## 🛠 Tech Stack
* **Java 17** (Azul Zulu)
* **Spring Boot 3.2.4**
* **Spring Security 6.x**
* **Data JPA** with **PostgreSQL**
* **MapStruct** (Entity-DTO mapping)
* **Lombok** (Boilerplate reduction)
* **JJWT 0.11.5** (JWT implementation)

---

## 🚦 Getting Started

### Prerequisites
* **PostgreSQL** running on `localhost:5432`
* A database named `openshelf`
* **Maven 3.x** and **JDK 17**

### Installation & Run
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/openshelf.git](https://github.com/your-username/openshelf.git)
   cd openshelf
2. **Build and Run:**
   ```bash
   ./mvnw clean compile
   ./mvnw spring-boot:run
