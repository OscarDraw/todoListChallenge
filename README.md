# ToDo List Web Application

This project implements a simple ToDo List web application using Spring Boot.

## Features

- **Task Management**: Create, edit, and delete tasks.
- **User Authentication**: Basic authentication for user login and registration.
- **Database Integration**: MySQL database configured with Spring Data JPA.
- **Frontend Integration**: Thymeleaf templates for rendering tasks and handling interactions.

## Setup

### Prerequisites

- JDK 11 or higher
- MySQL database (configured in `application.properties` with profiles for different environments using `spring.profiles.active=dev` for `application-dev.properties` or `application-prod.properties`)

### Running the Application

1. Clone the repository:
   git clone https://github.com/yourusername/todo-list.git
   cd todo-list

2. Build and run the application using Maven:
   ```mvn spring-boot:run```
   asegurar que la variable de entorno esté creada %MAVEN_HOME%\bin
   
4. Access the application at `http://localhost:8080`.
   
## Process Overview

1. **User Registration (Sign Up):**
   - Users register using the "Sign Up" form.
   - Upon registration, they are redirected to the main page.

2. **Main Page (ToDo List Overview):**
   - Users see a button to create new tasks.
   - Clicking opens a modal to asynchronously add task details.

3. **Creating a Task:**
   - Task creation updates the list asynchronously

4. **Editing a Task:**
   - Each task has an "Edit" button to update details in a modal asynchronously.

5. **Deleting a Task:**
   - "Delete" buttons remove tasks from the list asynchronously.

## Database Model
### User Table

| Column       | Type         | Constraints                        |
|--------------|--------------|------------------------------------|
| id           | BIGINT       | PRIMARY KEY, AUTO_INCREMENT         |
| username     | VARCHAR(255) | NOT NULL, UNIQUE                   |
| password     | VARCHAR(255) | NOT NULL                           |
| created_at   | TIMESTAMP    | NOT NULL, DEFAULT CURRENT_TIMESTAMP|
| updated_at   | TIMESTAMP    | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |
| user_id      | BIGINT       | NOT NULL                           |

### Task Table

| Column       | Type         | Constraints                        |
|--------------|--------------|------------------------------------|
| id           | BIGINT       | PRIMARY KEY, AUTO_INCREMENT         |
| title        | VARCHAR(255) | NOT NULL                           |
| description  | TEXT         |                                    |
| created_at   | TIMESTAMP    | NOT NULL, DEFAULT CURRENT_TIMESTAMP|
| updated_at   | TIMESTAMP    | NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |
| done         | BOOLEAN      | DEFAULT FALSE                      |
