# Spring Boot User & Post CRUD Project

Spring Boot와 Spring Data JPA를 학습하기 위해 만든 사용자 및 게시글 관리 REST API 프로젝트입니다.

사용자와 게시글의 CRUD 기능을 구현하고, Validation, 전역 예외 처리, HTTP 상태 코드, `ResponseEntity`, JPA Entity Relationship, Spring Data JPA Query Method 등을 학습했습니다.

## Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Project Structure

```text
example
│
├── SpringprojectApplication.java
│
├── Controller
│   ├── UsersController.java
│   └── PostController.java
│
├── Service
│   ├── UsersService.java
│   └── PostService.java
│
├── Repository
│   ├── UserRepository.java
│   └── PostRepository.java
│
├── Entity
│   ├── User.java
│   └── Post.java
│
├── DTO
│   ├── UserRequest.java
│   ├── UserResponse.java
│   ├── PostRequest.java
│   └── PostResponse.java
│
└── exception
    ├── UserNotFoundException.java
    ├── PostNotFoundException.java
    ├── GlobalExceptionHandler.java
    └── ErrorResponse.java
```

## Architecture

```text
Client
   ↓ HTTP Request
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA / Hibernate
   ↓
MySQL Database
```

## Exception Handling Flow

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Exception 발생
   ↓
GlobalExceptionHandler
   ↓
ErrorResponse
   ↓
JSON Response
```

## Implemented Features

### User CRUD

#### Create User

사용자 정보를 받아 데이터베이스에 저장합니다.

**Request**

```http
POST /users
Content-Type: application/json
```

```json
{
    "name": "minjae",
    "age": 24
}
```

**Response**

```json
{
    "name": "minjae",
    "age": 24,
    "message": "회원가입이 완료되었습니다."
}
```

#### Get User

사용자 ID를 이용하여 데이터베이스에서 특정 사용자를 조회합니다.

**Request**

```http
GET /users/{id}
```

Example:

```http
GET /users/1
```

**Response**

```json
{
    "name": "minjae",
    "age": 24,
    "message": "사용자 조회에 성공했습니다."
}
```

#### Update User

사용자 ID를 이용하여 기존 사용자의 정보를 수정합니다.

**Request**

```http
PUT /users/{id}
Content-Type: application/json
```

Example:

```http
PUT /users/1
```

```json
{
    "name": "kimminjae",
    "age": 25
}
```

**Response**

```json
{
    "name": "kimminjae",
    "age": 25,
    "message": "사용자 정보가 수정되었습니다."
}
```

#### Delete User

사용자 ID를 이용하여 데이터베이스에서 사용자를 삭제합니다.

**Request**

```http
DELETE /users/{id}
```

Example:

```http
DELETE /users/1
```

**Response**

```text
204 No Content
```

---

# Post CRUD

사용자와 게시글의 관계를 JPA Entity Relationship으로 구현하고 게시글 CR
