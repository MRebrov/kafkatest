package com.example.kafkatest.repository;

import com.example.kafkatest.entity.KafkaDltRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaDltRepository extends JpaRepository<KafkaDltRecord, Long> {
}

