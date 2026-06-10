# Condor Project

Condor is a distributed money transfer platform built with **Spring Boot**, **Kafka**, and **MySQL**.  
It supports deposits and withdrawals with a focus on scalability, security, and observability.

## 📦 Modules
- **condor-protobuf**: Protobuf definitions for Kafka messages.
- **condor-customers-manager**: REST APIs for customer management.
- **condor-transactions-manager**: Account and transaction APIs, schema migrations with Flyway.
- **condor-transactions-engine**: Asynchronous consumer that processes transactions from Kafka.
- **condor-docker**: Docker Compose setup for local and CI/CD environments.
- **condor-postman**: Postman collection for API testing.

## 🚀 Quick Start
To simplify startup, the `condor-docker` folder includes scripts for deployment:

- On Windows:
  ```bash
  deploy-all.bat
  ```
  
- On other operating systems, the script can be easily adapted.

Keep an eye on the logs, as the Condor containers depend on MySQL and will not start until the database passes its health check.

You can use the script `deploy-only-condor.bat` to start only the Condor components.

After deployment, import the Postman collection from condor-postman to test the APIs.

---

## 🧩 Runtime Microservices
- **condor-customers-manager**: APIs for managing customers.  
- **condor-transactions-manager**:  
  - Flyway creates schema and seed data (customers, accounts, transactions).  
  - Provides APIs to manage accounts and request transactions.  
  - Transactions are executed asynchronously: the API publishes to Kafka and returns a transaction ID. Status can be queried later.  
- **condor-transactions-engine**:  
  - Back-office processor subscribing to Kafka topics.  
  - Applies business logic and dispatches webhooks to notify clients when transactions are completed.

## 📈 Roadmap
Future improvements include:
- **Observability**: Integrate Prometheus + Grafana for monitoring.  
- **condor-gateway**: Single entry point with authentication (Keycloak), auditing, correlation IDs, mTLS, Message Level Encryption, and load balancing via Spring Cloud Gateway.  
- **condor-reports**: Reporting service backed by a data lake, fed asynchronously via Kafka.  
- **Database split**: Move away from shared DB to improve scalability and follow microservice best practices.  
- **Cache layer**: Introduce distributed caching with Redis, leveraging Spring abstractions.

## 🙌 Closing Note
This project showcases a practical and scalable architecture for a money transfer platform, balancing **performance**, **scalability**, and **client experience**. It is designed as an MVP but with a clear path toward enterprise-grade capabilities.

Thank you for reviewing this technical assessment.
