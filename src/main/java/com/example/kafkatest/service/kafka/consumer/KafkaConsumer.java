package com.example.kafkatest.service.kafka.consumer;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"test-topic"})
    public void consumeDto(Dto dto) {
        log.info("Received dto: {}", dto);
    }

    @KafkaListener(topics = {"test-topic-second"})
    public void consumeSecondDto(SecondDto secondDto) {
        log.info("Received secondDto: {}", secondDto);
    }
}
