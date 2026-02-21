# Secure Task Management System

A custom-built **Spring Boot 3.x** application featuring a robust **Spring Security** architecture and a high-performance **JPA Task Specification** engine. This project is designed to handle secure user lifecycle management and advanced data filtering.

## 🔐 Security Architecture

This project replaces standard security defaults with a custom-engineered identity layer:

- **Database-Driven Authentication:** Implements `CustomUserDetailsService` to authenticate users directly from the database using a `UserRepository`.
- **Password Encryption:** Utilizes `BCryptPasswordEncoder` to ensure all user credentials are encrypted before storage.
- **Access Control Logic:** Configures a `SecurityFilterChain` to manage public vs. protected resources, specifically permitting `/register`, `/login`, and `/signup` while protecting `/profile` and task-related data.
- **Role-Based Security:** Supports `USER` and `ADMIN` role assignment and authorization.

## 📝 Dynamic Task Engine

The task management module is built for scalability and precise data retrieval:

- **JPA Specifications:** Uses the **Criteria API** to build dynamic SQL queries on the fly based on user input.
- **Advanced Filtering:** Allows searching for tasks based on:
    - **User ID:** Ensures strict data isolation (users only see their own tasks).
    - **Status & Priority:** Filter by `TODO`, `IN_PROGRESS`, or `DONE` and priority levels.
    - **Date Ranges:** Filter tasks by `dueDate` using from/to ranges.
    - **Keyword Search:** Simultaneously searches for keywords in both the `title` and `description` fields.
- **Productivity Logic:** Includes dedicated specifications for identifying **Overdue Tasks** and tasks **Due Soon**.

## 🛠 Tech Stack

- **Java 17+**
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security (Custom Configuration)
- **Database:** Spring Data JPA / Hibernate
- **Build Tool:** Maven (including Maven Wrapper `mvnw`)

## 📂 Project Structure

src/main/java/
├── in.nabin.springsecurity/
│   ├── config/          # Spring Security configuration (SpringConfig)
│   ├── controller/      # AuthController (Register, Login, Profile)
│   ├── entities/        # AppUser JPA Entity
│   ├── repository/      # UserRepository (JPA interface)
│   └── service/         # CustomUserDetailsService logic
└── com.nabin.taskmanager/
    ├── controller/      # TaskController (Filtering, CRUD, Stats)
    └── specification/   # TaskSpecification (Dynamic filtering logic)
## 🚦 Getting Started

1. **Clone the project**
2. **Configure Environment:** Set your database credentials in `src/main/resources/application.properties`.
3. **Build with Maven Wrapper:**Bash
    
    `./mvnw clean install`
    
4. **Run Application:**Bash
    
    `./mvnw spring-boot:run`
