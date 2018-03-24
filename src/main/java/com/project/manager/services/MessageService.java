package com.project.manager.services;

import com.project.manager.controllers.MessageViewWindowController;
import com.project.manager.entities.Message;
import com.project.manager.models.MessageTableView;
import com.project.manager.repositories.MessageRepository;
import com.project.manager.sceneManager.SceneManager;
import com.project.manager.sceneManager.SceneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    private SessionService sessionService;

    private SceneManager sceneManager;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.sessionService = SessionService.getInstance();
        this.sceneManager = SceneManager.getInstance();
    }

    public List<Message> getAllReceivedMessages() {
        return messageRepository.findByReceiver(sessionService.getEmail());
    }

    public List<Message> getAllSentMessages() {
        return messageRepository.findBySender(sessionService.getEmail());
    }

    public void showMessageWindow(long l) {
        Optional<Message> message = Optional.of(messageRepository.getOne(l));
        message.ifPresent(m -> {
            MessageViewWindowController.messageToView = MessageTableView.convert(m);
            sceneManager.showInNewWindow(SceneType.MESSAGE_VIEW_WINDOW);
        });
    }
}
