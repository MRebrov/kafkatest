package com.example.kafkatest.service.kafka.consumer;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = {"topic1"})
    public void consumeDto(Dto dto, Acknowledgment acknowledgment) {
        log.info("Received dto: {}", dto);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = {"topic2"})
    public void consumeSecondDto(SecondDto secondDto, Acknowledgment acknowledgment) {
        log.info("Received secondDto: {}", secondDto);
        acknowledgment.acknowledge();
    }
}
