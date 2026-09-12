# UserApp

A basic user authentication web application built using Java Servlets, Spring Core, JDBC, and MySQL.

## Features

* User registration
* User login
* Form validation
* Username and email uniqueness validation
* Password confirmation
* Session-based authentication
* Protected dashboard
* User logout
* MySQL database integration

## Technologies Used

* Java
* Spring Core
* Java Servlets
* JDBC
* MySQL
* HTML
* CSS
* Apache Tomcat
* Maven

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/vansh/
    │       ├── dao/
    │       ├── model/
    │       ├── service/
    │       ├── servlet/
    │       └── AppConfig.java
    │
    └── webapp/
    │    ├── login.html
    │    ├── register.htm
    │    ├── user-created.html
    │    ├── style/
    │    └── WEB-INF/
    │        └── login-sucess.html
    │
    └── sql-structure/
         └──  structure.sql
```

## Application Flow

```text
HTML
  ↓
Servlet
  ↓
Service
  ↓
DAO
  ↓
JDBC
  ↓
MySQL
```

Spring Core is used for dependency injection and managing application components.

## Database

The application uses MySQL to store user information.

### Users Table

| Column     | Data Type | Description          |
| ---------- | --------- | -------------------- |
| username   | VARCHAR   | Unique username      |
| password   | VARCHAR   | User password        |
| first_name | VARCHAR   | User's first name    |
| last_name  | VARCHAR   | User's last name     |
| email      | VARCHAR   | User's email address |

The database connection uses environment variables:

```text
DB_USERNAME
DB_PASSWORD
```

<i>Note : See the sql-structure folder</i>

## Running the Project

1. Create the required MySQL database and `users` table.
2. Configure the `DB_USERNAME` and `DB_PASSWORD` environment variables.
3. Build the project using Maven.
4. Deploy the generated WAR file to Apache Tomcat.
5. Start the Tomcat server.
6. Open the application in a browser.

## Purpose

This project was created to understand the fundamentals of Java web development, Spring Core, Servlets, JDBC, database interaction, and session-based authentication without using Spring Boot.
