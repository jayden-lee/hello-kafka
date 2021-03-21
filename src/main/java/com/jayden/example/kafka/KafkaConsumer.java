package com.jayden.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.jayden.example.kafka.KafkaConstants.*;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = TOPIC_NAME)
    public void listenWithHeaders(
        @Payload String message,
        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

        log.info("Received Message: " + message + " from partition: " + partition);
    }
}
