# Equivalência de Disciplinas - Backend

## Technologies

- Java
- Maven
- Docker
- PostgreSQL
- Spring Boot
- Swagger

## Requirements

### 1. Set profile into environments

```
SPRING_PROFILES_ACTIVE=local;
API_KEY=exampleopenaikey;
DATABASE_HOST=127.0.0.1;
MAIL_PASSWORD=password;
MAIL_USERNAME=examplesender@gmail.com;
SECRET_JWT=SecretToSignToken;
OPEN_AI_MODEL=gpt-3.5-turbo
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