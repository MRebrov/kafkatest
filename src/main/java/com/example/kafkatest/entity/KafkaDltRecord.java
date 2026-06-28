package com.example.kafkatest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "kafka_dlt_records")
public class KafkaDltRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private int partitionId;
    private long offsetValue;

    @Column(columnDefinition = "TEXT")
    private String payload;

    @Column(columnDefinition = "TEXT")
    private String exceptionMessage;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors, Getters, Setters
}

