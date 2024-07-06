package com.devlearninglab.springbootmvcrest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@NamedQuery(query = "SELECT m from Message m", name = "Message.findAll")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NonNull
    @NotBlank(message = "Message cannot be blank!")
    @Size(min = 5, max = 255, message = "Message length must be between 5 and 255 characters")
    private String message;

    private LocalDateTime createdDate;
}
