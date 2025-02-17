package io.amudsno.kafkademo.service;

import io.amudsno.kafkademo.kafka.MyEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MyConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MyConsumerService.class);

    @KafkaListener(topics = "${my-properties.topic-name}")
    public void listen(ConsumerRecord<String, MyEvent> record) {
        logger.info("Received: {}", record);
    }

}
