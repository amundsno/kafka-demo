# Kafka Demo
A personal demo project to explore Kafka. So far, I've been experimenting with:
- Hosting Kafka clusters of various sizes in KRaft mode (no ZooKeeper) with Docker.
- Creating producers and consumers in Java with the Spring Boot Kafka integration.
- Using source/sink connectors to import/export data from/to external systems.

## Resources
There are tons of resources on Kafka online; not all of high quality. Learning Kafka from scratch, here are the ones I found most valuable, in recommended order:

1. Overview of what Kafka is, where and why it is needed, and how it works:
    - [Conduktor's Kafkademy](https://learn.conduktor.io/kafka/what-is-apache-kafka/)

2. Exploring the Kafka CLI with only one broker in Docker:
    - [Conduktor's Kafkademy Practise](https://learn.conduktor.io/kafka/how-to-start-kafka-using-docker/)
    - [CodingJigs setup a single-node KRaft cluster in Docker](https://codingjigs.com/setting-up-a-single-node-kafka-cluster-using-kraft-mode-no-more-zookeeper-dependency/)
    - [Apache Kafka's official Docker examples on GitHub](https://github.com/apache/kafka/blob/trunk/docker/examples/README.md)

3. Host Kafka clusters in KRaft mode with Docker Compose:
    - [CodingJigs setup multi-node KRaft cluster in Docker](https://codingjigs.com/a-practical-guide-to-setting-up-a-6-node-kraft-based-kafka-cluster/)
    - [Apache Kafka's official Docker examples on GitHub](https://github.com/apache/kafka/blob/trunk/docker/examples/README.md)
    - [Official Docker guide](https://docs.docker.com/guides/kafka/)

4. Spring Boot Kafka integration:
    - [Practical examples of producer/consumer configuration and message serialization](https://www.practicalsoftwarearchitecture.com/blog/spring-boot-kafka-config)
    - TODO: Error handling, dead letter queues, integration testing (test containers)

5. Kafka connectors:
    - [Practical crash course on a standalone file connector source/sink in Docker](https://dev.to/thegroo/kafka-connect-crash-course-1chd)
        - [Extended example for a scalable distributed connector in Docker](https://joelforjava.com/blog/2020/06/running-kafka-connect-from-a-container.html)
    - [Overview of connectors](https://docs.confluent.io/platform/current/connect/index.html)
    - TODO: Experiment with source/sink DB connectors

6. TODO: Kafka stream processing