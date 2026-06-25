package com.example.kafkatest.service.kafka.producer;

import com.example.kafkatest.configuration.KafkaTopics;
import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private KafkaTopics kafkaTopics;

    public KafkaProducer(KafkaTopics kafkaTopics, KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTopics = kafkaTopics;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceDto(Dto dto) {
        String topicName = kafkaTopics.topic1();
        send(topicName, dto.getClientId(), dto);
    }

    public void produceSecondDto(SecondDto secondDto) {
        String topicName = kafkaTopics.topic2();
        send(topicName, secondDto.getUserId(), secondDto);
    }

    private <T> void send(String topicName, String key, T dto) {
        kafkaTemplate.send(MessageBuilder.withPayload(dto)
                        .setHeader(KafkaHeaders.KEY, key)
                        .setHeader(KafkaHeaders.TOPIC, topicName)
                        .build())
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send event", ex);
                    }
                });
    }
}
