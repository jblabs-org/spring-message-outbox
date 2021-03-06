# Spring Message Outbox [![Build](https://github.com/jblabs-org/spring-message-outbox/workflows/Build%20and%20Test/badge.svg)](https://github.com/jblabs-org/spring-message-outbox/actions) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.jblabs/outbox-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.jblabs/)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=jblabs-org_spring-message-outbox&metric=coverage)](https://sonarcloud.io/dashboard?id=jblabs-org_spring-message-outbox) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=jblabs-org_spring-message-outbox&metric=security_rating)](https://sonarcloud.io/dashboard?id=jblabs-org_spring-message-outbox) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=jblabs-org_spring-message-outbox&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=jblabs-org_spring-message-outbox) 

Spring Message Outbox is a library that implements the
[outbox pattern](https://microservices.io/patterns/data/transactional-outbox.html) 
for Spring Boot.  This library is currently in an ALPHA state and in heavy development.  It is not
advisable to use this library in production.  Contributions or bug reports are welcome. 

## Usage
- Add outbox-core as a dependency and import its configuration class into a Spring configuration class 
  using @Import (don't name your config bean OutboxConfiguration as it will conflict).
- Add a storage module as a dependency and import its configuration class into a Spring configuration class 
  using @Import
- Add a publisher module as a dependency and import its configuration class into a Spring configuration class 
  using @Import
- Autowire the Outbox class and publish messages using outbox.publish().
  
## Core Module
- [Core Module](/outbox-core) - Implements the core workflow of the outbox pattern.

## Storage Modules
Storage modules implement the OutboxMessageRepository interface and provide persistence for the outbox messages
before they are published.

- [outbox-storage-postgres](/outbox-storage-postgres) - uses Postgresql to store outbox messages

## Publisher Modules
Publisher modules implement the OutboxMessagePublisher interface and provide a mechanism to publish outbox 
messages on a specific message channel.

- [outbox-publisher-logging](/outbox-publisher-logging) - Logs messages to the log instead of publishing.  Useful for testing.
- [outbox-publisher-rabbitmq](/outbox-publisher-rabbitmq) - Publishes messages to a RabbitMq exchange.

