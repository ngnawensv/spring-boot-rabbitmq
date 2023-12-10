package com.belrose.springbootrabbitmq.consumer;

import com.belrose.springbootrabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${spring.rabbitmq.queue.json.name}"})
    public  void  consume(User user){
        log.info(String.format("RabbitMQConsumer->consume(): Message received => %s",user.toString()));
    }
}
