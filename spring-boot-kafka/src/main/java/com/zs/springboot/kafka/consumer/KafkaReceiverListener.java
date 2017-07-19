package com.zs.springboot.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 消息消费监听
 */
@Component
public class KafkaReceiverListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiverListener.class);

    @KafkaListener(topics = {"topic1", "topic2"})
    public void processMessage(ConsumerRecord<?, ?> record) {
        LOGGER.info("receive topic='{}' message='{}'", record.topic(), record.value());
    }
}
