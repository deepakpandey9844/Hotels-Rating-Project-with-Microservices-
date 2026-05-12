# Hotel Rating System with Microservices

This project is a scalable and distributed Hotel Rating System built using the Microservices Architecture. It enables users to browse hotels, provide ratings, and view aggregated feedback. Each core functionality is encapsulated within its own microservice, ensuring modularity, scalability, and ease of maintenance.

## Project Structure

The system is composed of several independent services, each responsible for a specific domain. Below is an overview of each service and its primary responsibilities:

### 1. User Service

**Directory**: `UserService/`

**Responsibilities**:
- Manage user information, including creation, retrieval, updating, and deletion of user profiles.
- Handle user authentication and authorization processes.

**Key Components**:
- `UserController.java`: Defines RESTful endpoints for user-related operations.
- `UserService.java`: Contains business logic for managing users.
- `UserRepository.java`: Interfaces with the database to perform CRUD operations on user data.

### 2. Hotel Service

**Directory**: `HotelService/`

**Responsibilities**:
- Manage hotel data, including adding new hotels and retrieving hotel details.

**Key Components**:
- `HotelController.java`: Defines RESTful endpoints for hotel-related operations.
- `HotelService.java`: Contains business logic for hotel management.
- `HotelRepository.java`: Interfaces with the database to perform CRUD operations on hotel data.

### 3. Rating Service

**Directory**: `RatingService/`

**Responsibilities**:
- Handle user ratings and reviews for hotels.
- Aggregate ratings to provide overall scores for hotels.

**Key Components**:
- `RatingController.java`: Defines RESTful endpoints for rating-related operations.
- `RatingService.java`: Contains business logic for managing ratings and reviews.
- `RatingRepository.java`: Interfaces with the database to perform CRUD operations on rating data.

### 4. Service Registry (Eureka Server)

**Directory**: `ServiceRegistry/`

**Responsibilities**:
- Act as a discovery server where all microservices register themselves.
- Enable dynamic discovery of services, allowing for load balancing and failover.

**Key Components**:
- `ServiceRegistryApplication.java`: Bootstraps the Eureka Server with the `@EnableEurekaServer` annotation.
- `application.yml`: Configuration file specifying Eureka server settings.

### 5. API Gateway

**Directory**: `ApiGateway/`

**Responsibilities**:
- Serve as a single entry point for all client requests.
- Route requests to the appropriate microservice based on URL patterns.
- Handle cross-cutting concerns such as authentication, logging, and rate limiting.

**Key Components**:
- `ApiGatewayApplication.java`: Bootstraps the API Gateway.
- `application.yml`: Configuration file defining routing rules and filters.

### 6. Config Server

**Directory**: `ConfigServer/`

**Responsibilities**:
- Provide centralized configuration management for all microservices.
- Allow dynamic reloading of configurations without redeploying services.

**Key Components**:
- `ConfigServerApplication.java`: Bootstraps the Config Server with the `@EnableConfigServer` annotation.
- `application.yml`: Configuration file specifying the location of configuration repositories.

## Inter-Service Communication

The microservices communicate with each other using RESTful APIs. The API Gateway routes external requests to the appropriate service based on predefined routes. Services register themselves with the Eureka Server upon startup and discover other services through it, facilitating load balancing and failover mechanisms.

## Configuration Management

The Config Server manages external configurations for all services, enabling a centralized approach to handle environment-specific settings. This setup allows for dynamic configuration updates without the need to restart services.

## Fault Tolerance

To ensure resilience and fault tolerance, the system can integrate with tools like Hystrix or Resilience4j. These libraries provide mechanisms such as circuit breakers, retries, and fallbacks to handle potential failures gracefully.

## Security

Security can be implemented using Spring Security, incorporating OAuth2 or JWT tokens for authentication and authorization. The API Gateway can serve as the central point for validating tokens and managing user sessions.

## Monitoring

Monitoring and metrics can be achieved using tools like Spring Boot Actuator in conjunction with Prometheus and Grafana. These tools provide insights into application performance and health, enabling proactive issue detection and resolution.
