package com.bksoft.questionbank.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("📩 Received message: " + message);
    }
}
