package com.example.kafkatest.service.kafka.producer;

import com.example.kafkatest.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Dto> kafkaTemplate;

    public void produce(Dto dto) {
        String topicName = "test-topic";
        kafkaTemplate.setDefaultTopic(topicName);
        kafkaTemplate.send(MessageBuilder.withPayload(dto).build());
    }
}
