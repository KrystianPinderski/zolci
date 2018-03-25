package com.project.manager.services;

import com.project.manager.entities.Message;
import com.project.manager.repositories.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Mock
    private SessionService sessionService;

    /**
     * This test is need to be repair
     */
    @Test
    public void getAllReceivedMessagesTest() {
        String email = getExampleMessages().get().get(0).getReceiver();

        when(sessionService.getEmail()).thenReturn(email);
        when(messageRepository.findByReceiver(email)).thenReturn(getExampleMessages());

        List<Message> receivedList = messageService.getAllReceivedMessages();

        assertNotNull(receivedList);
        assertEquals(receivedList.get(0).getUsers(), getExampleMessages().get().get(0).getUsers());

    }

    public Optional<List<Message>> getExampleMessages() {
        Optional<List<Message>> messages = Optional.of(new ArrayList<Message>());
        messages.get().add(Message
                .builder()
                .id(1l)
                .sender("sender@mail.com")
                .receiver("receiver@mail.com")
                .title("title")
                .contents("contents")
                .sentDate(LocalDateTime.now())
                .build());

        messages.get().add(Message
                .builder()
                .id(2l)
                .sender("sender2@mail.com")
                .receiver("receiver2@mail.com")
                .title("title2")
                .contents("contents2")
                .sentDate(LocalDateTime.now())
                .build());
        return messages;
    }
}
