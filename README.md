# Spring Boot User CRUD Project

Spring Boot와 Spring Data JPA를 학습하기 위해 만든 간단한 사용자 관리 REST API 프로젝트입니다.

사용자 생성(Create), 사용자 조회(Read), 사용자 수정(Update), 사용자 삭제(Delete) 기능을 구현하고, Validation과 전역 예외 처리, HTTP 상태 코드 및 `ResponseEntity`를 이용한 응답 처리까지 학습했습니다.

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
├── DTO
│   ├── UserRequest.java
│   └── UserResponse.java
│
└── exception
    ├── UserNotFoundException.java
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

### Exception Handling Flow

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

## Exception Handling

사용자가 존재하지 않는 경우 `UserNotFoundException`을 발생시키고 `GlobalExceptionHandler`에서 예외를 처리합니다.

### User Not Found

```http
GET /users/999
```

존재하지 않는 사용자를 조회하면 `404 Not Found` 상태 코드를 반환합니다.

**Response**

```json
{
    "status": 404,
    "messages": [
        "사용자를 찾을 수 없습니다."
    ]
}
```

### Global Exception Handler

`@RestControllerAdvice`를 이용하여 Controller에서 발생하는 예외를 전역적으로 처리합니다.

```text
UserNotFoundException
        ↓
GlobalExceptionHandler
        ↓
ErrorResponse
        ↓
404 Not Found
```

## Validation

`spring-boot-starter-validation`을 사용하여 사용자 입력값을 검증합니다.

### UserRequest Validation

```java
@NotBlank(message = "이름은 필수입니다.")
private String name;

@Min(value = 0, message = "나이는 0 이상이어야 합니다.")
@Max(value = 100, message = "나이는 100 이하이어야 합니다.")
private int age;
```

Controller에서 `@Valid`를 사용하여 요청 데이터를 검증합니다.

```java
public ResponseEntity<UserResponse> createUser(
        @RequestBody @Valid UserRequest userRequest)
```

### Validation Error Handling

Validation에 실패하면 `MethodArgumentNotValidException`이 발생하고 `GlobalExceptionHandler`에서 처리합니다.

여러 validation 오류가 발생할 경우 모든 오류 메시지를 `List<String>`으로 반환합니다.

**Example Request**

```json
{
    "name": "",
    "age": 150
}
```

**Response**

```json
{
    "status": 400,
    "messages": [
        "이름은 필수입니다.",
        "나이는 100 이하이어야 합니다."
    ]
}
```

## HTTP Status Code

HTTP 상태 코드의 의미에 맞게 API 응답을 처리하도록 구현했습니다.

| Method     | 상황            | Status            |
| ---------- | ------------- | ----------------- |
| POST       | 사용자 생성 성공     | `201 Created`     |
| GET        | 사용자 조회 성공     | `200 OK`          |
| PUT        | 사용자 수정 성공     | `200 OK`          |
| DELETE     | 사용자 삭제 성공     | `204 No Content`  |
| GET        | 존재하지 않는 사용자   | `404 Not Found`   |
| POST / PUT | Validation 실패 | `400 Bad Request` |

## ResponseEntity

`ResponseEntity`를 사용하여 HTTP 응답의 상태 코드와 Body를 직접 설정할 수 있도록 구현했습니다.

### 200 OK

```java
return ResponseEntity.ok(userResponse);
```

### 201 Created

```java
return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(userResponse);
```

### 204 No Content

```java
return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
```

`ResponseEntity`를 통해 HTTP 응답의 다음 요소를 제어할 수 있습니다.

```text
ResponseEntity
 ├── Status
 ├── Header
 └── Body
```

## ErrorResponse

예외 발생 시 일관된 JSON 형식으로 응답하기 위해 `ErrorResponse` DTO를 사용합니다.

```java
public class ErrorResponse {

    private int status;
    private List<String> messages;

    ...
}
```

**Example**

```json
{
    "status": 400,
    "messages": [
        "이름은 필수입니다.",
        "나이는 100 이하이어야 합니다."
    ]
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
UserRepository.deleteById()
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
* `@Valid`를 이용한 요청 데이터 검증
* Validation Exception 처리
* Custom Exception 생성 및 처리
* `@RestControllerAdvice`를 이용한 전역 예외 처리
* `ErrorResponse`를 이용한 일관된 오류 응답 구현
* HTTP Status Code의 의미 이해
* `ResponseEntity`를 이용한 HTTP 응답 제어
* Java 객체가 JSON 응답으로 변환되는 과정 이해

## Next Steps

* 모든 사용자 조회 API 개선
* Validation Error 응답 구조 개선
* JPA Entity Relationship 학습

  * `@ManyToOne`
  * `@OneToMany`
  * `@OneToOne`
* Post CRUD 구현
* Pagination
* 사용자 검색 기능
* 비밀번호 암호화
* 회원가입 / 로그인 기능
* Spring Security 학습
* Session / JWT 학습
* REST API 프로젝트 확장
