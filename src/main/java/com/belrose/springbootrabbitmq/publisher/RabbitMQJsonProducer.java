package com.belrose.springbootrabbitmq.publisher;

import com.belrose.springbootrabbitmq.config.RabbitMQConfig;
import com.belrose.springbootrabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonProducer {

    private final RabbitMQConfig rabbitMQConfig;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitMQConfig rabbitMQConfig, RabbitTemplate rabbitTemplate) {
        this.rabbitMQConfig = rabbitMQConfig;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user){
        log.info(String.format("RabbitMQProducer->sendMessage(): Json message sent =>%s",user.toString()));
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(),rabbitMQConfig.getRoutingJsonKey(),user);
    }
}
