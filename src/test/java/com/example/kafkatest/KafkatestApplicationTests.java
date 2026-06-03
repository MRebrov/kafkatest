package com.example.kafkatest;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.service.kafka.consumer.KafkaConsumer;
import com.example.kafkatest.service.kafka.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessagingException;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class KafkatestApplicationTests {

    @Autowired
    private KafkaProducer producer;

    @MockitoSpyBean
    private KafkaConsumer consumer;

    @Test
    void shouldRetry() {
        doThrow(new MessagingException("EXCEPTION"))
                .doThrow(new MessagingException("EXCEPTION"))
                .doCallRealMethod()
                .when(consumer)
                .consumeDto(any());

        producer.produceDto(new Dto("CLIENT-TEST-ID", "TEST-MESSAGE"));

        await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() ->
                        verify(consumer, times(3))
                                .consumeDto(any())
                );
    }
}
