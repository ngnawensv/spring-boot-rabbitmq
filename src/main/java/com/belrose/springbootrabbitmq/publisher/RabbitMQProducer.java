package com.belrose.springbootrabbitmq.publisher;

import com.belrose.springbootrabbitmq.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    private final RabbitMQConfig rabbitMQConfig;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        log.info(String.format("RabbitMQProducer->sendMessage(): message sent =>%s",message));
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(),rabbitMQConfig.getRoutingKey(),message);
    }
}
