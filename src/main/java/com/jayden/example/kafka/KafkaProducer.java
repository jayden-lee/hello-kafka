package com.jayden.example.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message)
            .addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable e) {
                    log.error("Unable to send message=[" + message + "] due to : " + e.getMessage(), e);
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }
            });
    }
}
