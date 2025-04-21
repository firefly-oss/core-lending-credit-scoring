# Core Lending Credit Scoring Service

## Overview
The Core Lending Credit Scoring Service is a microservice component of the Firefly platform that provides credit scoring functionality for lending applications. This service manages credit scoring models, cases, requests, results, and bureau calls through a reactive RESTful API.

## Architecture

### Modules
The service is structured as a multi-module Maven project:

1. **core-lending-credit-scoring-interfaces**: Contains DTOs (Data Transfer Objects) and enums that define the service's API contracts.
2. **core-lending-credit-scoring-models**: Contains database entities and repositories for data persistence.
3. **core-lending-credit-scoring-core**: Contains business logic and service implementations.
4. **core-lending-credit-scoring-web**: Contains REST controllers and the application entry point.

### Technology Stack
- **Java 21**: Utilizing the latest Java features including virtual threads
- **Spring Boot**: Framework for building microservices
- **Spring WebFlux**: Reactive web framework
- **R2DBC**: Reactive Relational Database Connectivity for non-blocking database access
- **PostgreSQL**: Relational database for data storage
- **Flyway**: Database migration tool
- **OpenAPI/Swagger**: API documentation
- **Maven**: Build and dependency management

## Setup and Installation

### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher
- PostgreSQL 13 or higher

### Environment Variables
The following environment variables need to be set:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=credit_scoring
DB_USERNAME=postgres
DB_PASSWORD=your_password
DB_SSL_MODE=disable
```

### Building the Application
```bash
mvn clean install
```

### Running the Application
```bash
mvn spring-boot:run -pl core-lending-credit-scoring-web
```

### Docker
A Dockerfile is provided to containerize the application:

```bash
docker build -t core-lending-credit-scoring .
docker run -p 8080:8080 --env-file .env core-lending-credit-scoring
```

## API Documentation

The service provides a RESTful API with the following main resources:

1. **Scoring Models**: Credit scoring models with different approaches and dimensions
2. **Scoring Cases**: Cases that group related scoring requests
3. **Scoring Requests**: Individual credit scoring requests
4. **Scoring Results**: Results of credit scoring calculations
5. **Bureau Calls**: Records of calls to credit bureaus

When running the application, the API documentation is available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

### API Versioning
The API is versioned with the URL path pattern `/api/v1/...`

### Resource Hierarchy
The API follows a hierarchical structure:
- Scoring Cases
  - Scoring Requests
    - Scoring Results
  - Bureau Calls

## Development Guidelines

### Profiles
The application supports multiple profiles:
- **dev**: Development environment with detailed logging
- **testing**: Testing environment
- **prod**: Production environment with minimal logging and disabled Swagger UI

### Logging
Logging levels can be configured in the application.yaml file. In development mode, detailed logging is enabled for the application and database operations.

### Testing
Unit and integration tests should be written for all new functionality. Run tests with:

```bash
mvn test
```

## Monitoring and Health Checks

The application exposes health and metrics endpoints:
- Health: http://localhost:8080/actuator/health
- Info: http://localhost:8080/actuator/info
- Prometheus metrics: http://localhost:8080/actuator/prometheus

## Dependencies

The service depends on:
- `lib-parent-pom`: Parent POM with common dependencies and configurations
- `lib-utils`: Common utility library
