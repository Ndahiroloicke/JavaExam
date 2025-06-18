# Library Management System

A simple Library Management System built with Spring Boot, Hibernate, and JPA. This system allows you to manage books, borrow books, and track borrowing history via RESTful API endpoints.

## Features
- Add new books
- Retrieve book details by ISBN
- Check book availability
- Borrow books
- Return books
- Track borrowing transactions

## Technologies Used
- Spring Boot
- Hibernate (JPA)
- PostgreSQL
- Lombok

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL

### Database Setup
1. Start PostgreSQL.
2. Create a database named `library_management`:
   ```sql
   CREATE DATABASE library_management;
   ```
3. Update `src/main/resources/application.properties` with your PostgreSQL username and password if needed.

### Running the Application
```
mvn spring-boot:run
```
The server will start at `http://localhost:8080`.

## API Endpoints

### 1. Create a Book
- **POST** `/api/books`
- **Body Example:**
  ```json
  {
    "title": "The Life of Bedo",
    "author": "Ndahiro Mukunzi Loicke bedo",
    "isbn": "978-0743273566",
    "availabilityStatus": "AVAILABLE"
  }
  ```

### 2. Get Book by ISBN
- **GET** `/api/books/{isbn}`
- **Example:**
  ```
  GET http://localhost:8080/api/books/978-0743273566
  ```

### 3. Check Book Availability
- **GET** `/api/books/{isbn}/availability`
- **Example:**
  ```
  GET http://localhost:8080/api/books/978-0743273566/availability
  ```

### 4. Borrow a Book
- **POST** `/api/transactions/borrow?isbn={isbn}&borrowerName={name}`
- **Example:**
  ```
  POST http://localhost:8080/api/transactions/borrow?isbn=978-0743273566&borrowerName=John Doe
  ```

### 5. Return a Book
- **POST** `/api/transactions/{transactionId}/return`
- **Example:**
  ```
  POST http://localhost:8080/api/transactions/1/return
  ```

## Example Books

### 1. The Life of Bedo
```json
{
  "title": "The Life of Bedo",
  "author": "Ndahiro Mukunzi Loicke bedo",
  "isbn": "978-0743273566",
  "availabilityStatus": "AVAILABLE"
}
```

### 2. Java Book
```json
{
  "title": "Java Book",
  "author": "Larissa",
  "isbn": "978-0743273564",
  "availabilityStatus": "AVAILABLE"
}
```

## Notes
- ISBN must be unique for each book.
- You must create the database before running the application.
- All endpoints return JSON responses.

---

**Enjoy managing your library!** 