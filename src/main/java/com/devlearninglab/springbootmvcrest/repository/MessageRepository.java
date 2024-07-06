package com.devlearninglab.springbootmvcrest.repository;

import com.devlearninglab.springbootmvcrest.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(name = "Message.findAll")
    List<Message> findAllMessages();
    List<Message> findByMessageContaining(String keyword);
    List<Message> findByCreatedDateBefore(LocalDateTime localDateTime);
}
