# Project Documentation

## Table of Contents
- [Project Summary](#project-summary)
- [UserController](#usercontroller)
- [ToDoController](#todocontroller)
- [GlobalExceptionHandler](#globalexceptionhandler)

## Project Summary

- **Name:** todo-management
- **Description:** Demo project for Spring Boot ToDO Management
- **Java Version:** 17

## UserController

This controller manages user-related operations such as user registration, retrieval, update, and deletion.

- **Base Path:** `/api/users`

### Endpoints

1. [Register User](#register-user)
2. [Get User by ID](#get-user-by-id)
3. [Delete User](#delete-user)
4. [Update User](#update-user)

### Register User

- **Path:** `POST /api/users`
- **Description:** Registers a new user.
- **Request Body:** UserCreationDto
- **Response:** UserResponseDto
- **HTTP Method:** `POST`
- **HTTP Status Codes:** 
  - `201 CREATED` (Success)
  - `400 BAD REQUEST` (Invalid request)
  - `409 CONFLICT` (User already exists)

### Get User by ID

- **Path:** `GET /api/users/{id}`
- **Description:** Retrieves user details by ID.
- **Path Variable:** id (User ID)
- **Response:** UserResponseDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (User not found)

### Delete User

- **Path:** `DELETE /api/users/{id}`
- **Description:** Deletes a user by ID.
- **Path Variable:** id (User ID)
- **Response:** String message
- **HTTP Method:** `DELETE`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (User not found)

### Update User

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

1. [Add Todo](#add-todo)
2. [Add Todo With User](#add-todo-with-user)
3. [Get Todo by ID](#get-todo-by-id)
4. [Get All Todo Items](#get-all-todo-items)
5. [Update Todo](#update-todo)
6. [Delete Todo](#delete-todo)

### Add Todo

- **Path:** `POST /api/todos`
- **Description:** Adds a new todo item.
- **Request Body:** ToDoDto
- **Response:** ToDoDto
- **HTTP Method:** `POST`
- **HTTP Status Codes:** 
  - `201 CREATED` (Success)
  - `400 BAD REQUEST` (Invalid request)

### Add Todo With User

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

### Get Todo by ID

- **Path:** `GET /api/todos/{id}`
- **Description:** Retrieves todo item details by ID.
- **Path Variable:** id (Todo ID)
- **Response:** ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK` (Success)
  - `404 NOT FOUND` (Todo item not found)

### Get All Todo Items

- **Path:** `GET /api/todos`
- **Description:** Retrieves all todo items.
- **Response:** List of ToDoDto
- **HTTP Method:** `GET`
- **HTTP Status Codes:** 
  - `200 OK`

### Update Todo

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

### Delete Todo

- **Path:** `DELETE /api/todos/{id}`
- **Description:** Deletes a todo item by ID.
- **Path Variable:** id (Todo ID)
- **Response:** String message
- **HTTP Method:** `DELETE`
- **HTTP Status Codes:** 
  - `200 OK`

## GlobalExceptionHandler

This class handles global exceptions and provides customized error responses.

### Responsibilities:

- Catches specific exceptions and generates appropriate error responses.
- Provides consistent error handling across the application.

### Exception Handlers

1. [Handle EmailAlreadyTakenException](#handle-emailalreadytakenexception)
2. [Handle ResourceNotFoundException](#handle-resourcenotfoundexception)
3. [Handle UsernameAlreadyTakenException](#handle-usernamealreadytakenexception)
4. [Handle UserNotFoundException](#handle-usernotfoundexception)
5. [Handle Global Exception](#handle-global-exception)

### Handle EmailAlreadyTakenException

- **Exception:** EmailAlreadyTakenException
  - **Description:** Handles the case when a user tries to register with an email that is already taken.
  - **Response:** ErrorDetails with HttpStatus.CONFLICT (409)
  - **Error Code:** "EMAIL_TAKEN"

### Handle ResourceNotFoundException

- **Exception:** ResourceNotFoundException
  - **Description:** Handles the case when a requested resource is not found.
  - **Response:** ErrorDetails with HttpStatus.NOT_FOUND (404)
  - **Error Code:** "NOT_FOUND"

### Handle UsernameAlreadyTakenException

- **Exception:** UsernameAlreadyTakenException
  - **Description:** Handles the case when a user tries to register with a username that is already taken.
  - **Response:** ErrorDetails with HttpStatus.CONFLICT (409)
  - **Error Code:** "USERNAME_TAKEN"

### Handle UserNotFoundException

- **Exception:** UserNotFoundException
  - **Description:** Handles the case when a requested user is not found.
  - **Response:** ErrorDetails with HttpStatus.NOT_FOUND (404)
  - **Error Code:** "USER_NOT_FOUND"

### Handle Global Exception

- **Exception:** Exception (Fallback for all other exceptions)
  - **Description:** Handles all other exceptions that are not explicitly handled.
  - **Response:** ErrorDetails with HttpStatus.INTERNAL_SERVER_ERROR (500)
  - **Error Code:** "INTERNAL_ERROR"






