# DS Commerce API

A modern e-commerce REST API built with Spring Boot, featuring secure OAuth2/JWT authentication, comprehensive product catalog management, and order processing capabilities.

## 🚀 Features

- **Product Management**: Browse and search products with categories
- **Order System**: Create and manage customer orders with items and payments
- **User Management**: User registration and profile management
- **Security**: OAuth2 authorization server with JWT token authentication
- **Validation**: Input validation using Jakarta Bean Validation
- **RESTful API**: Clean, standard REST endpoints
- **H2 Database**: In-memory database for development and testing
- **CORS Support**: Cross-origin resource sharing for frontend integration

## 🛠️ Technology Stack

### Core Framework
- **Spring Boot 4.0.1** - Application framework
- **Java 21** - Programming language
- **Maven** - Build and dependency management

### Data & Persistence
- **Spring Data JPA** - Data access layer
- **H2 Database** - Embedded relational database
- **Hibernate Validator** - Bean validation

### Security
- **Spring Security** - Authentication and authorization
- **Spring Authorization Server** - OAuth2 authorization server
- **Spring Security OAuth2 Resource Server** - JWT token validation

### Testing
- **Spring Boot Test** - Testing framework
- **Spring Security Test** - Security testing utilities

## 📋 Project Structure

```
src/
├── main/
│   ├── java/com/caue/democommerce/
│   │   ├── config/                 # Spring configuration classes
│   │   │   ├── AuthorizationServerConfig.java
│   │   │   ├── ResourceServerConfig.java
│   │   │   └── customgrant/
│   │   ├── controllers/            # REST API endpoints
│   │   │   ├── CategoryController.java
│   │   │   ├── OrderController.java
│   │   │   ├── ProductController.java
│   │   │   ├── UserController.java
│   │   │   └── handlers/           # Exception handlers
│   │   ├── services/               # Business logic
│   │   │   ├── AuthService.java
│   │   │   ├── CategoryService.java
│   │   │   ├── OrderService.java
│   │   │   ├── ProductService.java
│   │   │   └── UserService.java
│   │   ├── repositories/           # Data access layer
│   │   │   ├── CategoryRepository.java
│   │   │   ├── OrderRepository.java
│   │   │   ├── ProductRepository.java
│   │   │   └── UserRepository.java
│   │   ├── dto/                    # Data Transfer Objects
│   │   ├── entities/               # JPA entities
│   │   │   ├── Category.java
│   │   │   ├── Product.java
│   │   │   ├── Order.java
│   │   │   ├── OrderItem.java
│   │   │   ├── Payment.java
│   │   │   ├── User.java
│   │   │   └── Role.java
│   │   ├── exceptions/             # Custom exceptions
│   │   ├── projections/            # Query projections
│   │   └── DemoCommerceApplication.java
│   └── resources/
│       ├── application.properties
│       ├── application-test.properties
│       └── import.sql              # Sample data
└── test/
    └── java/com/caue/democommerce/
```

## 🔐 Security Configuration

### Authentication Flow
The application uses OAuth2 with JWT tokens for stateless authentication:

1. **Authorization Server**: Issues JWT tokens to authenticated clients
2. **Resource Server**: Validates JWT tokens on protected endpoints
3. **Custom Grant**: Support for client credentials and other grant types

### Default Credentials
```
Client ID: dscommerce
Client Secret: dscommercesecret
```

### JWT Configuration
- **Token Duration**: 86400 seconds (24 hours) - configurable via `JWT_DURATION` environment variable

## ⚙️ Configuration

### Application Properties

The application uses environment-based configuration:

```properties
# Profile (test/prod)
APP_PROFILE=test

# OAuth2 Credentials
CLIENT_ID=dscommerce
CLIENT_SECRET=dscommercesecret

# JWT Token Duration (in seconds)
JWT_DURATION=86400

# CORS Origins
CORS_ORIGINS=http://localhost:8080,http://localhost:5173
```

### Setting Environment Variables
```bash
# Windows PowerShell
$env:APP_PROFILE = "test"
$env:CLIENT_ID = "dscommerce"
$env:CLIENT_SECRET = "dscommercesecret"
$env:JWT_DURATION = "86400"
$env:CORS_ORIGINS = "http://localhost:8080,http://localhost:5173"
```

## 📚 API Endpoints

### Products
- `GET /api/products` - List all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product (Admin)
- `PUT /api/products/{id}` - Update a product (Admin)
- `DELETE /api/products/{id}` - Delete a product (Admin)

### Categories
- `GET /api/categories` - List all categories
- `GET /api/categories/{id}` - Get category by ID
- `POST /api/categories` - Create a new category (Admin)
- `PUT /api/categories/{id}` - Update a category (Admin)
- `DELETE /api/categories/{id}` - Delete a category (Admin)

### Orders
- `GET /api/orders` - List user's orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update order status (Admin)

### Users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Register a new user
- `PUT /api/users/{id}` - Update user profile

### Authentication
- `POST /oauth2/token` - Get JWT token (OAuth2)

## 📊 Data Model

### Core Entities

**User**
- Contains user account information and authentication data
- Linked to roles for authorization

**Product**
- E-commerce product with price and description
- Belongs to a category
- Can have multiple order items

**Category**
- Product categorization
- One-to-many relationship with products

**Order**
- Customer order containing multiple items
- Tracks order status and payment
- Belongs to a user

**OrderItem**
- Individual items within an order
- Contains quantity and unit price
- Links order to products

**Payment**
- Payment information for orders
- Tracks payment status

**Role**
- User authorization roles
- Many-to-many with users

## 📝 License

This project is part of the DevSuperior Spring Boot course.

## 🎓 Learning Resources

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [OAuth2 & JWT Best Practices](https://oauth.net/)
- [RESTful API Design Guidelines](https://restfulapi.net/)

---

**Last Updated**: March 2026  
**Version**: 0.0.1-SNAPSHOT
