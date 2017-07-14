package com.zs.springboot.kafka;

import com.zs.springboot.kafka.producer.KafkaSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKafkaApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Autowired
    private KafkaSenderService senderService;

    /**
     * 测试发送消息到 Kafka，消息会被 KafkaReceiverListener 接收
     *
     * @throws IOException
     */
    @Test
    public void sendKafka() throws IOException, InterruptedException {
        // 发送消息数量
        int num = 10;
        // topic 与 KafkaReceiverListener 中 topics 相对应
        String topic1 = "topic1";
        for (int i = 0; i < num; i++) {
            senderService.sendMessage(topic1, LocalDateTime.now().toString() + "_" + i);
        }

        String topic2 = "topic2";
        for (int i = 0; i < num; i++) {
            senderService.sendMessage(topic2, LocalDateTime.now().toString() + "_" + i);
        }

        // 阻塞，使消息可以被监听接收
        System.in.read();
    }

}
