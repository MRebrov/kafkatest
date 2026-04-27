package com.example.kafkatest.configuration;

import com.example.kafkatest.dto.Dto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, Dto> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        // ... other config (bootstrap servers, etc.)

        // Create the deserializer manually to avoid ClassLoader issues
        JsonDeserializer<Dto> jsonDeserializer = new JsonDeserializer<>(Dto.class);
        jsonDeserializer.addTrustedPackages("*"); // Or your specific package

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                jsonDeserializer
        );
    }

}
