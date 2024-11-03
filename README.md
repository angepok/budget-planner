# Budget Planner API

## Overview
The Budget Planner API is a RESTful API built with Java and Spring Boot, designed to help users manage their income and expenses efficiently. This API allows users to perform full CRUD operations on their financial data while providing insights into their budgeting through various endpoints.

## Features
- **Full CRUD Operations**: Create, Read, Update, and Delete operations for Income and Expense records.
- **Data Persistence**: Uses MySQL for data storage.
- **Filtering**: Filter expenses and income by year and month using custom Spring Data JPA queries.
- **Exception Handling**: Robust error handling to ensure a user-friendly experience.
- **Inheritance**: Demonstrates inheritance in the model design.
- **Documentation**: Comprehensive documentation with Swagger UI.

## Getting Started

### Prerequisites
Before you begin, ensure you have the following installed:
- **JDK 21** or higher
- **Maven**
- **MySQL Database**
- **Git**
- **Visual Studio Code** (or your preferred IDE)

### Installation
1. Clone the repository:
    ```bash
    git clone <repository-url>
    cd <repository-folder>
    ```

2. Set up your MySQL database and update the `application.properties` file with your database connection details:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3307/budget_planner
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    ```

3. Build the project and skip tests:
    ```bash
    mvn clean install -DskipTests
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints
### Income
- **Create Income**: `POST /api/income`
- **Get All Income Records**: `GET /api/income`
- **Get Income by ID**: `GET /api/income/{id}`
- **Update Income**: `PUT /api/income/{id}`
- **Delete Income**: `DELETE /api/income/{id}`

### Expense
- **Create Expense**: `POST /api/expense`
- **Get All Expenses**: `GET /api/expense`
- **Get Expense by ID**: `GET /api/expense/{id}`
- **Update Expense**: `PUT /api/expense/{id}`
- **Delete Expense**: `DELETE /api/expense/{id}`

### Budget
- **Get Budget for Month**: `GET /api/budget/{year}/{month}`

## Swagger Documentation
Swagger is integrated into the application for API documentation. You can access it by navigating to the following URL in your browser: http://localhost:8080/swagger-ui/index.html
