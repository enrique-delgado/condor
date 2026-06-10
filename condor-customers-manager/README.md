# Condor - Customers Manager

This module exposes APIs for customer administration in the Condor platform.  

## Features
- REST APIs for CRUD operations on customers
- Password encryption with Argon2
- Validation with Bean Validation (JSR‑303/JSR‑380)
- Exception handling with i18n messages
- Unit and integration tests with JUnit + Mockito
- Karate DSL tests for API validation and load testing
- Docker support

## APIs
- `GET /customers/{customer-id}`
- `POST /customers`
- `PUT /customers/{customer-id}`
- `DELETE /customers/{customer-id}`
