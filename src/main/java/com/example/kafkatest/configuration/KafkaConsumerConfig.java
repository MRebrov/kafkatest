package com.example.kafkatest.configuration;

import com.example.kafkatest.entity.KafkaDltRecord;
import com.example.kafkatest.repository.KafkaDltRepository;
import com.fasterxml.jackson.core.JsonParseException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public <V> ConsumerFactory<String, V> consumerFactory(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public <V> ConcurrentKafkaListenerContainerFactory<String, V> kafkaListenerContainerFactory(
            ConsumerFactory<String, V> consumerFactory,
            DefaultErrorHandler errorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    @Bean
    <V> DefaultErrorHandler errorHandler(KafkaTemplate<String, V> kafkaTemplate, KafkaDltRepository dltRepository) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate) {

            @Override
            public void accept(ConsumerRecord<?, ?> record, Exception exception) {
                KafkaDltRecord dltRecord = new KafkaDltRecord();
                dltRecord.setTopic(record.topic());
                dltRecord.setPartitionId(record.partition());
                dltRecord.setOffsetValue(record.offset());
                dltRecord.setPayload(record.value() != null ? record.value().toString() : null);
                dltRecord.setExceptionMessage(exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage());

                dltRepository.save(dltRecord);
            }
        };
        var errorHandler = new DefaultErrorHandler(recoverer, new FixedBackOff(100L, 2));

        errorHandler.addNotRetryableExceptions(
                IllegalArgumentException.class,
                JsonParseException.class
        );
        return errorHandler;
    }
}
