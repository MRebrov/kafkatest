package com.example.kafkatest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.producer.topics")
public record KafkaTopics(
        String topic1,
        String topic2,
        String topic3
) {
}
