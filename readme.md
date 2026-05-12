# Order Management Microservice

A simple Order Management Microservice built using Spring Boot and Java 17.

This project demonstrates clean layered architecture, REST API design, validation, exception handling, pagination, filtering, and thread-safe in-memory storage using `ConcurrentHashMap`.

---

# Tech Stack

- Java 17
- Spring Boot 4
- Gradle
- REST APIs
- ConcurrentHashMap
- JUnit 5

---

# Features

- Create Order
- Get Order By ID
- Update Order Status
- List Orders with Pagination
- Filtering using Specification-style logic
- Global Exception Handling
- Request Validation
- UUID-based Order IDs
- Thread-safe In-memory Storage
- Dummy Data Generator API
- Service Layer Unit Testing



# Architecture

The application follows a clean layered architecture:

Controller → Service → Repository

## Layers

### Controller Layer
Handles incoming HTTP requests and responses.

### Service Layer
Contains business logic and status transition validation.

### Repository Layer
Manages in-memory data storage using `ConcurrentHashMap`.

### Specification Layer
Handles filtering logic for orders.

### Exception Layer
Provides centralized exception handling using `@RestControllerAdvice`.

---

# Project Structure

```text
src/main/java/com/reflectionsglobal/assignment
│
├── controller
│   └── OrderController
│
├── service
│   ├── OrderService
│   └── impl
│       └── OrderServiceImpl
│
├── repository
│   ├── OrderRepository
│   └── InMemoryOrderRepository
│
├── specification
│   └── OrderSpecification
│
├── model
│   ├── Order
│   └── OrderStatus
│
├── dto
│   ├── CreateOrderRequest
│   ├── UpdateStatusRequest
│   ├── OrderResponse
│   └── PaginatedResponse
│
├── exception
│   ├── OrderNotFoundException
│   ├── InvalidStatusTransitionException
│   ├── ErrorResponse
│   └── GlobalExceptionHandler
│
├── util
│   ├── OrderIdGenerator
│   └── PaginationUtil
│
└── AssignmentApplication




---

# PART 3 — APIs + Setup + Testing

```
# API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/orders` | Create new order |
| GET | `/orders/{id}` | Get order by ID |
| PUT | `/orders/{id}/status` | Update order status |
| GET | `/orders` | Get all orders with pagination/filter |
| POST | `/orders/dummy` | Insert 500 dummy records |

---

# API Examples

## Create Order

### Request

POST `/orders`

```json
{
  "customerName": "Saurabh",
  "amount": 5000
}