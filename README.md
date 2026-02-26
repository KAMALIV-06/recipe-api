# Recipe Data Collection and API Development

This project parses a large JSON dataset of recipes, stores the data in a MySQL database, and exposes REST APIs to manage and retrieve recipe information.

The application is built using Spring Boot with a clean layered architecture and follows proper separation of concerns.

---

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA
* MySQL
* Jackson (JSON parsing)
* Lombok
* Maven

---
# GIT REPO LINK: 

## Project Overview

The project performs two major operations:

1. JSON Data Parsing and Database Ingestion
2. REST API Implementation for Managing Recipes

---

## Application Architecture

The project follows a layered architecture:


controller/   → Handles HTTP requests  
service/      → Contains business logic  
repository/   → Database interaction using JPA  
entity/       → Maps Java objects to database tables  
dto/          → Request and response data transfer objects  
util/         → JSON parsing logic using CommandLineRunner  

---

## Application Flow

### 1. JSON Parsing Flow (On Application Startup)

* The application uses CommandLineRunner.
* On startup, JsonDataLoader executes automatically.
* The JSON file (recipes.json) is loaded from the resources folder.
* Jackson ObjectMapper converts JSON → DTO.
* DTO objects are mapped to Entity objects.
* Data is stored in the database using repository.saveAll().
* The insertion runs only if the table is empty (to prevent duplicate entries).

---

### 2. API Flow

Client → Controller → Service → Repository → Database
Database → Repository → Service → Controller → Client

* Controller handles HTTP requests.
* DTO validates and transfers data.
* Service contains business logic (e.g., calculating total_time).
* Repository interacts with the database.
* ResponseDTO is returned to the client.

---

## Database Setup

### Step 1: Create Database

```sql
CREATE DATABASE recipes;
```

### Step 2: Use Database

```sql
USE recipes;
```

### Step 3: Create Table

```sql
CREATE TABLE recipes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    cuisine VARCHAR(255) NOT NULL,
    rating FLOAT,
    prep_time INT NOT NULL,
    cook_time INT NOT NULL,
    total_time INT,
    description TEXT,
    nutrients JSON,
    serves VARCHAR(100)
);
```

---

## Configuration

Update `application.properties` with your database credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/recipes
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

---

## Running the Application

1. Clone the repository:

```
git clone <repository-url>
```

2. Navigate to project directory:

```
cd recipe-api
```

3. Build the project:

```
mvn clean install
```

4. Run the application:

```
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

## API Endpoints

### 1. POST /recipes

Description: Add a new recipe.

Request Body Example:

```json
{
  "title": "Chocolate Cake",
  "cuisine": "Dessert",
  "prep_time": 20,
  "cook_time": 40,
  "description": "Delicious cake",
  "nutrients": {
    "calories": "500 kcal",
    "carbohydrateContent": "60 g",
    "proteinContent": "6 g",
    "fatContent": "25 g"
  },
  "serves": "6 servings"
}
```

Successful Response (201 Created):

```json
{
  "id": 6968,
  "title": "Chocolate Cake",
  "cuisine": "Dessert",
  "prep_time": 20,
  "cook_time": 40,
  "total_time": 60,
  "description": "Delicious cake",
  "nutrients": {
    "calories": "500 kcal",
    "carbohydrateContent": "60 g",
    "proteinContent": "6 g",
    "fatContent": "25 g"
  },
  "serves": "6 servings"
}
```

Validation Failure Example (400 Bad Request):

```json
{
  "title": "Title is required"
}
```

---

### 2. GET /recipes/top?limit=5

Description: Retrieve top-rated recipes sorted by rating in descending order.

Example Request:

```
GET http://localhost:8080/recipes/top?limit=3
```

Example Response:

```json
{
  "data": [
    { recipe_object_1 },
    { recipe_object_2 },
    { recipe_object_3 }
  ]
}
```

If limit is not provided, default value is 5.

---

## Design Decisions

* DTOs are used to separate API contract from database entities.
* Business logic (such as total_time calculation) is handled in the service layer.
* Nutrients are stored as JSON in the database for flexible nested data storage.
* Pageable is used to perform sorting and limiting at the database level for better performance.
* CommandLineRunner is used for automatic data ingestion on startup.

---

## Notes

* The JSON file must be placed inside `src/main/resources`.
* Data ingestion runs only when the recipes table is empty.
* The project follows clean layered architecture and separation of concerns.

---

## Author

Kamali V         
Department: Information Technology   
RMK Engineering College


