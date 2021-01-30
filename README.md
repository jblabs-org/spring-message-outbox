# Spring Message Outbox
Spring Message Outbox is a library that implements the
[outbox pattern](https://microservices.io/patterns/data/transactional-outbox.html) 
for Spring Boot.

## Usage
- Add outbox-core as a dependency to your project.
- Add a storage module as a dependency and import its configuration class into a Spring configuration class 
  using @Import
- Add a publisher module as a dependency and import its configuration class into a Spring configuration class 
  using @Import
- Autowire the Outbox class and publish messages using outbox.publish().
  

## Core Module
The core module contains the main interfaces that other modules implement, classes that implement the main 
outbox pattern workflows, and some default implementations of interfaces such as the Json serializer.

### Storage Modules
Storage modules implement the OutboxMessageRepository interface and provide persistence for the outbox messages
before they are published.

- outbox-storage-postgres: uses Postgresql to store outbox messages

### Publisher Modules
Publisher modules implement the OutboxMessagePublisher interface and provide a mechanism to publish outbox 
messages on a specific message channel.

- outbox-publisher-logging: Logs messages to the log instead of publishing.  Useful for testing.
- outbox-publisher-rabbitmq: Publishes messages to a RabbitMq exchange.

