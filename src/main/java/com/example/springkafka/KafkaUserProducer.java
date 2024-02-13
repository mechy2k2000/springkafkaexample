package com.example.springkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaUserProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.queue-topic-name}")
    private String queueTopicName;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaUserProducer.class);


    private KafkaTemplate<String, Object> kafkaTemplate;

@Autowired
public void KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void addToQueue(String message) {
        LOGGER.info((String.format("Item add to queue %s", message)));
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        kafkaTemplate.send(queueTopicName, user.toString());
    }
}
