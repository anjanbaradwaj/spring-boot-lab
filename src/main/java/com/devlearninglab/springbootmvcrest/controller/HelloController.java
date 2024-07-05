package com.devlearninglab.springbootmvcrest.controller;


import com.devlearninglab.springbootmvcrest.model.Message;
import com.devlearninglab.springbootmvcrest.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{id}")
    public String getMessage(Model model, @PathVariable Long id) {
        Message message = messageService.getMessage(id);
        model.addAttribute("message", message.getMessage());
        return "hello-world";
    }

    @PostMapping("/messages/add")
    public String addMessage(@Valid @RequestBody  Message message, BindingResult result, Model model) {
        System.out.println(" --------------------> " + message);
        if (result.hasErrors()) {
            model.addAttribute("message", "Validation failed: " + result.getAllErrors());
            return "hello-world";
        }
        messageService.saveMessage(message);
        model.addAttribute("message", "Message added successfully!");
        return "hello-world";
    }
}
