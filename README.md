## Introduction

Welcome to the **ToDO Management** project! This project was created as a learning tool to gain hands-on experience with Spring Boot, a popular Java-based framework used for building web applications. The project focuses on creating a simple yet functional To-Do management system, allowing users to manage their tasks efficiently.

### Key Features:
- **User Management:** Register, update, retrieve, and delete user accounts.
- **To-Do Management:** Add, update, retrieve, and delete to-do items.
- **Search Functionality:** Search for to-do items based on a query.
- **User-Specific To-Dos:** View to-do items specific to a user, including completed and incomplete tasks.

### Technologies Used:
- **Spring Boot:** For building the backend services.
- **Spring Data JPA:** For database interactions.
- **Spring Security:** For securing the application.
- **MySQL:** As the relational database.
- **ModelMapper:** For object mapping.
- **Lombok:** To reduce boilerplate code.

# Project Documentation

## Table of Contents
- [Project Summary](#project-summary)
- [UserController](#usercontroller)
  - [Endpoints](#endpoints)
    - [Register User](#register-user)
    - [Get User by ID](#get-user-by-id)
    - [Delete User](#delete-user)
    - [Update User](#update-user)
- [ToDoController](#todocontroller)
  - [Endpoints](#endpoints-1)
    - [Add Todo](#add-todo)
    - [Add Todo With User](#add-todo-with-user)
    - [Get Todo by ID](#get-todo-by-id)
    - [Get All Todo Items](#get-all-todo-items)
    - [Update Todo](#update-todo)
    - [Delete Todo](#delete-todo)
    - [Complete Todo](#complete-todo)
    - [InComplete Todo](#incomplete-todo)
    - [Search Todo](#search-todo)
    - [Get Todos by User](#get-todos-by-user)
    - [Get Completed Todos by User](#get-completed-todos-by-user)
    - [Get InCompleted Todos by User](#get-incompleted-todos-by-user)
- [GlobalExceptionHandler](#globalexceptionhandler)
  - [Responsibilities](#responsibilities)
  - [Exception Handlers](#exception-handlers)
    - [Handle EmailAlreadyTakenException](#handle-emailalreadytakenexception)
    - [Handle ResourceNotFoundException](#handle-resourcenotfoundexception)
    - [Handle UsernameAlreadyTakenException](#handle-usernamealreadytakenexception)
    - [Handle UserNotFoundException](#handle-usernotfoundexception)
    - [Handle Global Exception](#handle-global-exception)
  - [Custom Exceptions](#custom-exceptions)
    - [EmailAlreadyTakenException](#emailalreadytakenexception)
    - [ResourceNotFoundException](#resourcenotfoundexception)
    - [UsernameAlreadyTakenException](#usernamealreadytakenexception)
    - [UserNotFoundException](#usernotfoundexception)
- [Dependencies](#dependencies)
- [Build Configuration](#build-configuration)

## Project Summary

- **Name:** todo-management
- **Description:** Demo project for Spring Boot ToDO Management
- **Java Version:** 17

## UserController

This controller manages user-related operations such as user registration, retrieval, update, and deletion.

- **Base Path:** `/api/users`

### Endpoints

#### Register User
- **Path:** `POST /api/users`
- **Description:** Registers a new user.
- **Request Body:** UserCreationDto
- **Response:** UserResponseDto
- **HTTP Method:** `POST`
- **HTTP Status Codes:** 
  - `201 CREATED` (Success)
  - `400 BAD REQUEST` (Invalid request)
  - `409 CONFLICT` (User already exists)

#### Get User by ID
- **Path:** `GET /api/users/{id}`
- **Description:** Retrieves user details by ID.
- **Path Variable:** id (User ID)
- **Response:** UserResponseDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (User not found)

#### Delete User
- **Path:** `DELETE /api/users/{id}`
- **Description:** Deletes a user by ID.
- **Path Variable:** id (User ID)
- **Response:** String message
- **HTTP Method:** `DELETE`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (User not found)

#### Update User
- **Path:** `PUT /api/users/{id}`
- **Description:** Updates user details by ID.
- **Path Variable:** id (User ID)
- **Request Body:** UserResponseDto
- **Response:** UserResponseDto
- **HTTP Method:** `PUT`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `400 BAD REQUEST` (Invalid request)
  - `404 NOT FOUND` (User not found)

## ToDoController

This controller manages todo item-related operations such as adding, retrieving, updating, and deleting todo items.

- **Base Path:** `/api/todos`

### Endpoints

#### Add Todo
- **Path:** `POST /api/todos`
- **Description:** Adds a new todo item.
- **Request Body:** ToDoDto
- **Response:** ToDoDto
- **HTTP Method:** `POST`
- **HTTP Status Codes:** 
  - `201 CREATED` (Success)
  - `400 BAD REQUEST` (Invalid request)

#### Add Todo With User
- **Path:** `POST /api/todos/{id}`
- **Description:** Adds a new todo item for a specific user.
- **Path Variable:** id (User ID)
- **Request Body:** ToDoDto
- **Response:** ToDoDto
- **HTTP Method:** `POST`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `400 BAD REQUEST` (Invalid request)
  - `404 NOT FOUND` (User not found)

#### Get Todo by ID
- **Path:** `GET /api/todos/{id}`
- **Description:** Retrieves todo item details by ID.
- **Path Variable:** id (Todo ID)
- **Response:** ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (Todo item not found)

#### Get All Todo Items
- **Path:** `GET /api/todos`
- **Description:** Retrieves all todo items.
- **Response:** List of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

#### Update Todo
- **Path:** `PUT /api/todos/{id}`
- **Description:** Updates todo item details by ID.
- **Path Variable:** id (Todo ID)
- **Request Body:** ToDoDto
- **Response:** ToDoDto
- **HTTP Method:** `PUT`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `400 BAD REQUEST` (Invalid request)
  - `404 NOT FOUND` (Todo item not found)

#### Delete Todo
- **Path:** `DELETE /api/todos/{id}`
- **Description:** Deletes a todo item by ID.
- **Path Variable:** id (Todo ID)
- **Response:** String message
- **HTTP Method:** `DELETE`
- **HTTP Status Codes:** 
  - `200 OK`

#### Complete Todo
- **Path:** `PATCH /api/todos/{id}/complete`
- **Description:** Marks a todo item as complete.
- **Path Variable:** id (Todo ID)
- **Response:** ToDoDto
- **HTTP Method:** `PATCH`
- **HTTP Status Codes:** 
  - `200 OK`

#### InComplete Todo
- **Path:** `PATCH /api/todos/{id}/in-complete`
- **Description:** Marks a todo item as incomplete.
- **Path Variable:** id (Todo ID)
- **Response:** ToDoDto
- **HTTP Method:** `PATCH`
- **HTTP Status Codes:** 
  - `200 OK`

#### Search Todo
- **Path:** `GET /api/todos/search`
- **Description:** Searches todo items based on a query.
- **Request Param:** query (Search query)
- **Response:** List of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

#### Get Todos by User
- **Path:** `GET /api/todos/user/{id}`
- **Description:** Retrieves all todo items for a specific user.
- **Path Variable:** id (User ID)
- **Response:** Set of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

#### Get Completed Todos by User
- **Path:** `GET /api/todos/user/complete/{id}`
- **Description:** Retrieves completed todo items for a specific user.
- **Path Variable:** id (User ID)
- **Response:** Set of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

#### Get InCompleted Todos by User
- **Path:** `GET /api/todos/user/in-complete/{id}`
- **Description:** Retrieves incomplete todo items for a specific user.
- **Path Variable:** id (User ID)
- **Response:** Set of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

## GlobalExceptionHandler

This class handles global exceptions and provides customized error responses.

### Responsibilities
- Catches specific exceptions and generates appropriate error responses.
- Provides consistent error handling across the application.

### Exception Handlers

#### Handle EmailAlreadyTakenException
- **Exception:** EmailAlreadyTakenException
- **Description:** Handles the case when a user tries to register with an email that is already taken.
- **Response:** ErrorDetails with HttpStatus.CONFLICT (409)
- **Error Code:** "EMAIL_TAKEN"

#### Handle ResourceNotFoundException
- **Exception:** ResourceNotFoundException
- **Description:** Handles the case when a requested resource is not found.
- **Response:** ErrorDetails with HttpStatus.NOT_FOUND (404)
- **Error Code:** "NOT_FOUND"

#### Handle UsernameAlreadyTakenException
- **Exception:** UsernameAlreadyTakenException
- **Description:** Handles the case when a user tries to register with a username that is already taken.
- **Response:** ErrorDetails with HttpStatus.CONFLICT (409)
- **Error Code:** "USERNAME_TAKEN"

#### Handle UserNotFoundException
- **Exception:** UserNotFoundException
- **Description:** Handles the case when a requested user is not found.
- **Response:** ErrorDetails with HttpStatus.NOT_FOUND (404)
- **Error Code:** "USER_NOT_FOUND"

#### Handle Global Exception
- **Exception:** Exception (Fallback for all other exceptions)
- **Description:** Handles all other exceptions that are not explicitly handled.
- **Response:** ErrorDetails with HttpStatus.INTERNAL_SERVER_ERROR (500)
- **Error Code:** "INTERNAL_ERROR"

### Custom Exceptions

#### EmailAlreadyTakenException
- **Description:** Thrown when a user tries to register with an email that is already taken.
- **Response:** Error message: "Email is already taken"
- **HTTP Status Code:** 409 CONFLICT

#### ResourceNotFoundException
- **Description:** Thrown when a requested resource is not found.
- **Response:** Error message: "Resource not found"
- **HTTP Status Code:** 404 NOT FOUND

#### UsernameAlreadyTakenException
- **Description:** Thrown when a user tries to register with a username that is already taken.
- **Response:** Error message: "Username is already taken"
- **HTTP Status Code:** 409 CONFLICT

#### UserNotFoundException
- **Description:** Thrown when a requested user is not found.
- **Response:** Error message: "User not found"
- **HTTP Status Code:** 404 NOT FOUND

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- MySQL Connector J (runtime scope)
- Lombok (optional)
- Spring Boot Starter Test (test scope)
- ModelMapper
- Spring Boot Starter Security

## Build Configuration

- **Build Tool:** Maven
- **Spring Boot Plugin:** spring-boot-maven-plugin
