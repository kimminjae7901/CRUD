# Spring Boot User CRUD Project

Spring Boot와 Spring Data JPA를 학습하기 위해 만든 간단한 사용자 관리 REST API 프로젝트입니다.

현재 사용자 생성(Create), 사용자 조회(Read), 사용자 수정(Update), 사용자 삭제(Delete) 기능을 구현했습니다.

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

### Update User

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

### Delete User

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
사용자 삭제가 완료되었습니다.
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

### Update

```text
PUT /users/{id}
    ↓
UsersController
    ↓
UsersService
    ↓
UserRepository.findById()
    ↓
User Entity 수정
    ↓
JPA / Hibernate
    ↓
MySQL
```

### Delete

```text
DELETE /users/{id}
    ↓
UsersController
    ↓
UsersService
    ↓
UserRepository.findById()
    ↓
UserRepository.delete()
    ↓
JPA / Hibernate
    ↓
MySQL
```

## CRUD API

| Method | Endpoint      | 기능        |
| ------ | ------------- | --------- |
| POST   | `/users`      | 사용자 생성    |
| GET    | `/users/{id}` | 특정 사용자 조회 |
| PUT    | `/users/{id}` | 사용자 정보 수정 |
| DELETE | `/users/{id}` | 사용자 삭제    |

## Learning Goals

* Spring Boot 기본 구조 이해
* Controller, Service, Repository 역할 이해
* Dependency Injection 이해
* JPA와 Entity의 기본 동작 이해
* DTO를 이용한 Request / Response 분리
* REST API와 HTTP 요청 방식 이해
* MySQL 데이터베이스 연동
* JPA를 이용한 CRUD 구현
* HTTP Method(POST, GET, PUT, DELETE)의 역할 이해
* Entity 조회 및 수정/삭제 과정 이해

## Next Steps

* Read All Users
* Exception Handling
* Validation
* HTTP Status Code 개선
* `ResponseEntity`를 이용한 응답 처리
* API 예외 상황 처리
* 사용자 목록 조회 및 검색 기능
