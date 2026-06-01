package com.example.kafkatest.service.kafka.consumer;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"test-topic", "test-topic-second"})
    public void consume(Object obj) {
        log.info("Received message: {}", obj);
        if (obj instanceof Dto dto) {
            log.info("Received dto: {}", dto);
        }
        if (obj instanceof SecondDto secondDto) {
            log.info("Received secondDto: {}", secondDto);
        }
        else {
            log.info("Received unknown type: {}", obj.getClass());
        }
    }
}
