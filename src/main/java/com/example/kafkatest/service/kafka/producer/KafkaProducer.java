package com.example.kafkatest.service.kafka.producer;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Dto> kafkaTemplateDto;

    @Autowired
    private KafkaTemplate<String, SecondDto> kafkaTemplateSecondDto;

    public void produceDto(Dto dto) {
        String topicName = "test-topic";
        kafkaTemplateDto.setDefaultTopic(topicName);
        kafkaTemplateDto.send(MessageBuilder.withPayload(dto)
                .setHeader(KafkaHeaders.KEY, dto.getClientId())
                .build());
    }

    public void produceSecondDto(SecondDto secondDto) {
        String topicName = "test-topic-second";
        kafkaTemplateSecondDto.setDefaultTopic(topicName);
        kafkaTemplateSecondDto.send(MessageBuilder.withPayload(secondDto)
                .setHeader(KafkaHeaders.KEY, secondDto.getUserId())
                .build());
    }
}
