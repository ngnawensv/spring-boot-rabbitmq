package com.belrose.springbootrabbitmq.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Data
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue.name}")
    private  String queue;

    @Value("${spring.rabbitmq.queue.json.name}")
    private  String jsonQueue;

    @Value("${spring.rabbitmq.exchange.name}")
    private  String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private  String routingKey;

    @Value("${spring.rabbitmq.routing.json.key}")
    private  String routingJsonKey;

    //spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(this.getQueue());
    }

    //spring bean for rabbitmq queue to store json message
    @Bean
    public Queue jsonQueue(){
        return new Queue(this.getJsonQueue());
    }


    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(this.getExchange());
    }

    //Binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(this.getRoutingKey());
    }

    //Binding between jsonQueue and exchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(this.getRoutingJsonKey());
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    // those 3 beans are autoconfigure, not explecite configuration require
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
