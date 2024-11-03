
# Budget Planner API

## Overview
This Budget Planner API is a RESTful web service built using Java and Spring Boot to help users manage their income and expenses. This API uses CRUD operations, allowing users to create, read, update, and delete records of their financial activities.

## Techonologies
- Java
- Spring Boot
- Maven
- MySQL

## Features
- **Full CRUD Operations**: Supports creating, reading, updating, and deleting both income and expense records.
- **Data Persistence**: Utilizes a MySQL database for storing financial data.
- **Filtering Capabilities**: Provides endpoints to filter expenses and income by year and month using Spring Data JPA.

## Getting Started

### Clone Repository
1. Fork this repository in your GitHub account
2. Clone your fork locally


## API Endpoints

### Income Endpoints
- `POST /api/income`: Create a new income record.
- `GET /api/income`: Retrieve all income records.
- `GET /api/income/{id}`: Retrieve a specific income record by ID.
- `PUT /api/income/{id}`: Update an existing income record.
- `DELETE /api/income/{id}`: Delete an income record by ID.

### Expense Endpoints
- `POST /api/expense`: Create a new expense record.
- `GET /api/expense`: Retrieve all expense records.
- `GET /api/expense/{id}`: Retrieve a specific expense record by ID.
- `PUT /api/expense/{id}`: Update an existing expense record.
- `DELETE /api/expense/{id}`: Delete an expense record by ID.

### Budget Endpoints
- `GET /api/budget/{year}/{month}`: Retrieve the budget for a specific year and month.


## Setup and Configuration
1. **Prerequisites**:
   - Java 11 or higher
   - MySQL database server
   - Maven for dependency management

2. **Configuration**:
   - Update the `application.properties` file with the correct database URL, username, and password.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3307/budget_planner
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the Application**:
   - Use your IDE to run the Spring Boot application or use the command line:
   ```bash
   mvn spring-boot:run
   ```

3. **Dumpfile location**: [text](src/main/java/com/myproject/budgetplanner/budgetplannerdumpfile.sql)
## Documentation
For detailed API documentation, Swagger (http://localhost:8080/swagger-ui/index.html#) is integrated into the project, providing a user-friendly interface to explore available endpoints and their parameters.
