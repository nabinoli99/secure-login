# 🔐 Secure Task Management System

A **Spring Boot 3.x** application featuring a **custom Spring Security architecture** and a high-performance **JPA Task Specification engine**. Designed for secure user lifecycle management and advanced task filtering with scalability in mind.

---

## 🛡 Security Architecture

This project replaces default Spring Security settings with a **custom-engineered identity layer** for enhanced control:

- **Database-Driven Authentication**
    
    Users are authenticated via `CustomUserDetailsService` directly from the database using `UserRepository`.
    
- **Password Encryption**
    
    All credentials are securely encrypted using `BCryptPasswordEncoder`.
    
- **Access Control**
    
    Configured via `SecurityFilterChain` to manage public and protected resources:
    
    - **Public:** `/register`, `/login`, `/signup`
    - **Protected:** `/profile`, task management endpoints
- **Role-Based Authorization**
    
    Supports `USER` and `ADMIN` roles with fine-grained access control.
    

---

## 📝 Dynamic Task Engine

Designed for **scalable, precise data retrieval** using JPA Specifications and the Criteria API:

- **Dynamic Filtering:** Search tasks based on:
    - **User ID:** Ensures users see only their tasks.
    - **Status & Priority:** Filter by `TODO`, `IN_PROGRESS`, `DONE` and priority levels.
    - **Date Ranges:** Filter by `dueDate` (from/to ranges).
    - **Keyword Search:** Searches `title` and `description` simultaneously.
- **Productivity Logic:**
    
    Dedicated specifications for **Overdue Tasks** and tasks **Due Soon**.
    

---

## 🛠 Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security (Custom Configuration)
- **Database:** Spring Data JPA / Hibernate
- **Build Tool:** Maven (with Maven Wrapper `mvnw`)

---

## 📂 Project Structure

```
src/main/java/
├── in.nabin.springsecurity/
│   ├── config/          # Spring Security configuration (SecurityFilterChain, PasswordEncoder)
│   ├── controller/      # AuthController (Register, Login, Profile)
│   ├── entities/        # AppUser JPA Entity
│   ├── repository/      # UserRepository (JPA interface)
│   └── service/         # CustomUserDetailsService logic
└── com.nabin.taskmanager/
    ├── controller/      # TaskController (CRUD, Filtering, Stats)
    └── specification/   # TaskSpecification (Dynamic filtering logic)
```

---

## 🚦 Getting Started

1. **Clone the repository**
    
    ```bash
    git clone <repository-url>
    cd <repository-folder>
    ```
    
2. **Configure Environment**
    
    Update `src/main/resources/application.properties` with your database credentials.
    
3. **Build Project**
    
    ```bash
    ./mvnw clean install
    ```
    
4. **Run Application**
    
    ```bash
    ./mvnw spring-boot:run
    ```
    
5. **Access Application**
    - **Public Endpoints:** `/register`, `/login`
    - **Protected Endpoints:** `/profile`, `/tasks`

---

## 🎯 Key Features

- **Custom Security**: Full control over authentication & authorization
- **Dynamic Task Filtering**: Advanced criteria-based querying
- **Overdue/Due Soon Tasks**: Automatically identifies critical tasks
- **Role-Based Access**: Fine-grained control for USER & ADMIN
- **Scalable Design**: Optimized JPA specifications for large datasets
