# Spring Boot User CRUD Project

Spring Boot와 Spring Data JPA를 학습하기 위해 만든 간단한 사용자 관리 REST API 프로젝트입니다.

현재 사용자 생성(Create)과 특정 사용자 조회(Read) 기능을 구현했습니다.

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
│   └── UsersController.java
│
├── Service
│   └── UsersService.java
│
├── Repository
│   └── UserRepository.java
│
├── Entity
│   └── User.java
│
└── DTO
    ├── UserRequest.java
    └── UserResponse.java
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

## Implemented Features

### Create User

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

### Get User

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

## Database Flow

### Create

```text
POST /users
    ↓
UsersController
    ↓
UsersService
    ↓
UserRepository.save()
    ↓
JPA / Hibernate
    ↓
MySQL
```

### Read

```text
GET /users/{id}
    ↓
UsersController
    ↓
UsersService
    ↓
UserRepository.findById()
    ↓
Optional<User>
    ↓
UserResponse
    ↓
JSON Response
```

## Learning Goals

* Spring Boot 기본 구조 이해
* Controller, Service, Repository 역할 이해
* Dependency Injection 이해
* JPA와 Entity의 기본 동작 이해
* DTO를 이용한 Request / Response 분리
* REST API와 HTTP 요청 방식 이해
* MySQL 데이터베이스 연동

## Next Steps

* Read All Users
* Update User
* Delete User
* Exception Handling
* Validation
* HTTP Status Code 개선
