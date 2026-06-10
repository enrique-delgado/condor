# Condor Protobuf

This module contains **Protocol Buffers definitions** used across Condor microservices.  
- Kafka events:
  - `TransactionInitiatedEventProto`

[//]: # (  - `TransactionCompletedEventProto`.  )
[//]: # (- REST API messages:)
[//]: # (  - `CustomerProto`, `AccountProto`.)

## Features
- All generated classes end with suffix `Proto`.
- All packages start with `com.condor`.
- Compiles `.proto` files into Java classes using `protobuf-maven-plugin`.
