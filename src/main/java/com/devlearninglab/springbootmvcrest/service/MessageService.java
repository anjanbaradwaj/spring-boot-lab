package com.devlearninglab.springbootmvcrest.service;

import com.devlearninglab.springbootmvcrest.model.Message;
import com.devlearninglab.springbootmvcrest.repository.MessageRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message getMessage(Long id){
        return messageRepository.findById(id).orElse(new Message("Default Message"));
    }

    public void saveMessage(Message message){
        message.setCreatedDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    public void updateMessage(Message message, Long id) {
        Message msg = messageRepository.findById(id).get();
        msg.setMessage(message.getMessage());
        messageRepository.save(msg);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAllMessages();
    }

    public void deleteMessage(Long id) {
        Message message = messageRepository.findById(id).get();
        messageRepository.delete(message);
    }

    public List<Message> getMessageByKeyword(String keyword){
        return messageRepository.findByMessageContaining(keyword);
    }

    public List<Message> getMessageBeforeCreatedDate(LocalDateTime localDateTime){
        return messageRepository.findByCreatedDateBefore(localDateTime);
    }
}
