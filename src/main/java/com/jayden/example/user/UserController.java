package com.jayden.example.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayden.example.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayden.example.kafka.KafkaConstants.TOPIC_NAME;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity createUser(@RequestBody CreateUserRequest request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);

        kafkaProducer.sendMessage(TOPIC_NAME, message);

        return ResponseEntity.ok().build();
    }
}
