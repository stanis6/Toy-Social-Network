package com.example.toysocialnetwork.service;

import com.example.toysocialnetwork.domain.Message;
import com.example.toysocialnetwork.repository.db.DBMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {
    private final DBMessageRepository repository;

    public MessageService(DBMessageRepository repository) {
        this.repository = repository;
    }

    public List<Message> getMessages(Long idFrom, Long idTo) {
        return repository.getMessages(idFrom, idTo);
    }

    public void add(String message, Long idFrom, Long idTo, LocalDateTime dateTime) {
        Message msg = new Message(message, idFrom, idTo, dateTime);
        msg.setId(repository.getLowestFreeId()+1);
        repository.add(msg);
    }
}
