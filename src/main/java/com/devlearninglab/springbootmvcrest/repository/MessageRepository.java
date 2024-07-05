package com.devlearninglab.springbootmvcrest.repository;

import com.devlearninglab.springbootmvcrest.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
