# Outbox Core
Core module implementing the outbox pattern for use in Spring Boot projects. It contains the main 
interfaces that other modules implement, classes that implement the main outbox pattern workflows, and 
some default implementations of interfaces such as the Json messagePayloadSerializer.

## Usage

## Available Properties
- outbox.storage.polling.frequencyInMillis=1000 - controls how often the storage is polled for new messages
- outbox.storage.polling.batchSize=100 - number of messages pulled each poll from storage