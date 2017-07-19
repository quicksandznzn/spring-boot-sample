package com.zs.springboot.kafka.producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Kafka 消息发送
 *
 * @author yan.liang@ustcinfo.com
 * @date 2017/6/9
 */
@Component
public class KafkaSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSenderService.class);
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 多线程发送消息到 Kafka
     */
    public void sendMessage(String topic, String message) {
        executor.execute(() -> asyncSendMessage(topic, message));
        //  LOGGER.info("send message='{}' to topic='{}' ", message, topic); // 打印日志会影响发送效率
    }

    /**
     * 同步发送消息
     */
    public void syncSendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    /**
     * 异步发送消息
     */
    private void asyncSendMessage(String topic, String message) {
        // the KafkaTemplate provides asynchronous send methods returning a Future
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        // register a callback with the listener to receive the result of the send asynchronously
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER
                    .info("SUCCESS: send message='{}' to topic='{}' with offset={}", message, topic,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOGGER
                    .error("ERROR: unable to send message='{}' to topic='{}'", message, topic, ex);
            }
        });
    }

}
