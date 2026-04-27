package com.example.kafkatest.service.kafka.consumer;

import com.example.kafkatest.dto.Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"test-topic"}, groupId = "test-group-id")
    public void consume(Dto dto) {
        log.info("Received message: {}", dto);
    }
}
