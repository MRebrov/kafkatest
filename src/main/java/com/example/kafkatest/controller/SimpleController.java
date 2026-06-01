package com.example.kafkatest.controller;

import com.example.kafkatest.dto.Dto;
import com.example.kafkatest.dto.SecondDto;
import com.example.kafkatest.service.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> send(@RequestBody Dto dto) {
        kafkaProducer.produceDto(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/sendSecond")
    public ResponseEntity<HttpStatus> sendSecond(@RequestBody SecondDto secondDto) {
        kafkaProducer.produceSecondDto(secondDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
