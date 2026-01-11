package com.bksoft.questionbank.api.controller;

import com.bksoft.questionbank.rabbitmq.MessageProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqMessageController {
    private final MessageProducer producer;

    public RabbitMqMessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}
