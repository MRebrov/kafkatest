package com.example.kafkatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkatestApplicationTests {

	@Test
	void contextLoads() {
	}

	//TODO add test with retry when app started and kafka message was sent, but wasn't consumed properly
}
