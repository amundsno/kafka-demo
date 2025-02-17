package io.amudsno.kafkademo.controller;

import io.amudsno.kafkademo.kafka.MyEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class MyProducerController {
    private static final Logger logger = LoggerFactory.getLogger(MyProducerController.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${my-properties.topic-name}")
    private String topicName;

    @Value("${my-properties.messages-per-request}")
    private int messagesPerRequest;

    @GetMapping("/produce")
    public String produce() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(messagesPerRequest);
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> this.kafkaTemplate.send(
                                topicName, String.valueOf(i), new MyEvent(
                                        "Hello, Kafka consumer! This is producer message number " + i, i))
                        .whenComplete((result, ex) -> {
                            if (ex == null) {
                                latch.countDown();
                            } else {
                                logger.warn("Sending message threw exception: " + ex.getMessage());
                            }
                        }));
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All messages sent and acknowledged");
        return String.format("Produced %s messages in the '%s' topic!", messagesPerRequest, topicName);
    }
}
