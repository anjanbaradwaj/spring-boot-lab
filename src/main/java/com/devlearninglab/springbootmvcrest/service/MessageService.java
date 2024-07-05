package com.devlearninglab.springbootmvcrest.service;

import com.devlearninglab.springbootmvcrest.model.Message;
import com.devlearninglab.springbootmvcrest.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message getMessage(Long id){
        return messageRepository.findById(id).orElse(new Message("Default Message"));
    }

    public void saveMessage(Message message){
        messageRepository.save(message);
    }
}
