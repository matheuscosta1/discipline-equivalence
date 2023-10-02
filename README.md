# Equivalência de Disciplinas - Backend

## Technologies

- Java
- Maven
- Docker
- MongoDB
- Spring Boot
- Swagger

## Requirements

### 1. Set profile into environments

```
SPRING_PROFILES_ACTIVE=local
```

## Starting services

### 1. Build project

```
$ mvn clean install
```

### 2. Initializing the microservice

From the /backend directory
```
$ mvn spring-boot:run
```

## API Documentation

- Swagger url is available at {BaseUrl}/swagger-ui/