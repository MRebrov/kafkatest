package com.example.kafkatest;

import com.example.kafkatest.configuration.KafkaTopics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(KafkaTopics.class)
@SpringBootApplication
public class KafkatestApplication {

	static void main(String[] args) {
		SpringApplication.run(KafkatestApplication.class, args);
	}

}
