package com.devlearninglab.springbootmvcrest.controller;


import com.devlearninglab.springbootmvcrest.model.Message;
import com.devlearninglab.springbootmvcrest.service.MessageService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        Message message = messageService.getMessage(id);
        logger.info(" The message is {} ", message.getMessage());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/search")
    public ResponseEntity<List<Message>> getMessageByKeyword(@RequestParam String key) {
        List<Message> messageByKeyword = messageService.getMessageByKeyword(key);
        return ResponseEntity.ok(messageByKeyword);
    }

    @GetMapping("/messages/before")
    public ResponseEntity<List<Message>> getMessageBeforeDate(@RequestParam LocalDateTime localDateTime) {
        List<Message> messageBeforeCreatedDate = messageService.getMessageBeforeCreatedDate(localDateTime);
        return ResponseEntity.ok(messageBeforeCreatedDate);
    }

    @PostMapping("/messages/add")
    public ResponseEntity<String> addMessage(@Valid @RequestBody Message message, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>("Failed to save the message", HttpStatus.BAD_REQUEST);
        }
        messageService.saveMessage(message);
        return ResponseEntity.ok("Message added successfully!");
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<String> updateMessage(@PathVariable Long id, @RequestBody Message message, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Failed to update the message", HttpStatus.BAD_REQUEST);
        }
        messageService.updateMessage(message, id);
        return ResponseEntity.ok("Message updated successful!");
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message deleted Successfully!");
    }
}
