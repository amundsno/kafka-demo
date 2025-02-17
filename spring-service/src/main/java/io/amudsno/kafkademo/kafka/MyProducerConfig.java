package io.amudsno.kafkademo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyProducerConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    // Build kafka producer config by extending the default values
    // Object is used to enable the eventual Kafka template to work with any object
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    // Instantiate a default factory using our own producer configurations
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    // Instantiate the Kafka template using the producer factory we configured
    KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Value("${my-properties.topic-name}")
    private String topicName;

    @Value("${my-properties.partitions}")
    private int partitions;

    @Value("${my-properties.replication-factor}")
    private short replicationFactor;
    @Bean
    // Injecting a NewTopic bean to the Spring context instructs Kafka's AdminClient bean (already
    // loaded into the Spring context) to create a new topic
    public NewTopic myTopic() {
        return new NewTopic(topicName, partitions, replicationFactor);
    }
}
