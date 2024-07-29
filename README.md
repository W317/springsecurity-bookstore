# Bookstore Management System with Spring Security and JWT

## Introduction

This project implements a Bookstore Management System that allows digitization of book and author management. The system supports role-based access control with two roles: Librarian and Borrower. It uses Spring Security and JSON Web Tokens (JWT) for securing REST API endpoints.

## Features

### Core Functionalities

1. **Add New Books (Librarian)**
   - Allows librarians to add new books with details such as title and author.

2. **Add New Authors (Librarian)**
   - Allows librarians to add new authors with details like name.

3. **List All Books (Public)**
   - Provides a public API to list all available books.

4. **List All Authors (Public)**
   - Provides a public API to list all authors whose books are available.

5. **Borrow Book (Borrower)**
   - Allows borrowers to mark a book as borrowed.

6. **Log Execution Time**
   - Logs the execution time of each method for performance monitoring.

### Security and Authentication

1. **Spring Security Configuration**
   - Configures Spring Security to protect REST API endpoints.

2. **Login Endpoint**
   - `POST /api/login`: Returns a JWT with user information upon successful login.

3. **Role-Based Authorization**
   - Restricts access to specific endpoints based on user roles (Librarian or Borrower).

4. **Invalid Login Requests**
   - Returns appropriate HTTP status codes (e.g., 401 Unauthorized) and error messages for invalid login attempts.

## Setup and Installation

### Prerequisites

- JDK 11 or later
- Maven
- MySQL SQL database
- IDE (e.g., IntelliJ IDEA or Eclipse)

### Installation Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/W317/springsecurity-bookstore.git

2.  **Access the API**
- Login: POST /api/login
- Register (for borrowers): POST /api/register
- Add Books: POST /api/books
- Add Authors: POST /api/authors
- List Books: GET /api/books
- List Authors: GET /api/authors
- Borrow Book: POST /api/borrow/{id}
