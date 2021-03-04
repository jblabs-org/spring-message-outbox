# Outbox Publisher - RabbitMq
Publisher implementation for RabbitMQ.  

# Usage
- Import the configuration `````@Import(RabbitMqPublisherConfiguration.class)`````
- By default messages are published to an exchange with the same name as the aggregateName field in the
OutboxMessage.  To change this, create a bean that implements the ExchangeNameExtractor interface.