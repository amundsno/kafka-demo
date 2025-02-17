package io.amudsno.kafkademo.service;

import io.amudsno.kafkademo.kafka.MyEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MyConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MyConsumerService.class);

    /*
    // Bare-bones listener, using the default consumer configuration for deserialization
    @KafkaListener(topics = "${my-properties.topic-name}")
    public void listen(ConsumerRecord<String, MyEvent> record) {
        logger.info("Received: {}", record);
    }
     */

    @KafkaListener(
            topics = "${my-properties.topic-name}",
            clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory" // Inject the bean we defined in our configuration
    )
    public void listenAsJsonObject(ConsumerRecord<String, Object> record, @Payload MyEvent payload) {
        logger.info("Logger [JSON] received key: {} | payload: {} | record: {}", record.key(), payload, record);
    }

    @KafkaListener(
            topics = "${my-properties.topic-name}",
            clientIdPrefix = "string",
            containerFactory = "kafkaListenerStringContainerFactory"
    )
    public void listenAsString(ConsumerRecord<String, String> record, @Payload String payload) {
        logger.info("Logger [String] received key: {} | payload: {} | record: {}", record.key(), payload, record);
    }

    @KafkaListener(
            topics = "${my-properties.topic-name}",
            clientIdPrefix = "byte",
            containerFactory = "kafkaListenerByteArrayContainerFactory"
    )
    public void listenAsByteArray(ConsumerRecord<String, byte[]> record, @Payload byte[] payload) {
        logger.info("Logger [ByteArray] received key: {} | payload: {} | record: {}", record.key(), payload, record);
    }
}
