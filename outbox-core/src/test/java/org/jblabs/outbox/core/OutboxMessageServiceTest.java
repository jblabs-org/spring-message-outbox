package org.jblabs.outbox.core;

import org.jblabs.outbox.core.message.OutboxMessage;
import org.jblabs.outbox.core.publisher.MessagePublishingException;
import org.jblabs.outbox.core.publisher.OutboxMessagePublisher;
import org.jblabs.outbox.core.storage.OutboxMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OutboxMessageServiceTest {

    OutboxMessageService outboxMessageService;

    @Mock
    OutboxMessageRepository outboxMessageRepository;

    @Mock
    OutboxMessagePublisher outboxMessagePublisher;

    @BeforeEach
    private void init() {
        OutboxProperties outboxProperties = new OutboxProperties();
        outboxProperties.setBatchSize(10);
        outboxMessageService = new OutboxMessageService(outboxMessageRepository, outboxMessagePublisher,
                outboxProperties);
    }

    @Test
    void saveMessage() {
        OutboxMessage outboxMessage = outboxMessage();
        outboxMessageService.saveMessage(outboxMessage);
        Mockito.verify(outboxMessageRepository).saveMessage(outboxMessage);
    }

    @Test
    void saveMessages() {
        List<OutboxMessage> outboxMessages = new ArrayList<>();
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        outboxMessageService.saveMessages(outboxMessages);
        Mockito.verify(outboxMessageRepository).saveMessages(outboxMessages);
    }

    @Test
    void publishMessages() throws MessagePublishingException {
        List<OutboxMessage> outboxMessages = new ArrayList<>();
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        when(outboxMessageRepository.getMessages(anyInt())).thenReturn(outboxMessages);

        outboxMessageService.publishMessages();

        verify(outboxMessagePublisher).publish(outboxMessages.get(0));
        verify(outboxMessagePublisher).publish(outboxMessages.get(1));
        verify(outboxMessagePublisher).publish(outboxMessages.get(2));

        List<String> messageIds = outboxMessages.stream().map(OutboxMessage::getMessageId).collect(Collectors.toList());
        verify(outboxMessageRepository).markAsPublished(messageIds);
    }

    @Test
    void publishMessages_shouldNotMarkPublishedOnPublishingException() throws MessagePublishingException {
        List<OutboxMessage> outboxMessages = new ArrayList<>();
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        outboxMessages.add(outboxMessage());
        when(outboxMessageRepository.getMessages(anyInt())).thenReturn(outboxMessages);
        doThrow(new MessagePublishingException()).when(outboxMessagePublisher).publish(any());

        outboxMessageService.publishMessages();

        verify(outboxMessageRepository, times(0)).markAsPublished(any());
    }

    private OutboxMessage outboxMessage() {
        return OutboxMessage.rehydrate("123", "accountCreated", "account", "456", "account-topic",
                "testAccountPayload", OffsetDateTime.now(), false);
    }
}