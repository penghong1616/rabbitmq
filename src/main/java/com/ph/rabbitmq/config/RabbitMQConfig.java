package com.ph.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 交换机和队列绑定
 */
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange-name}")
    private String exchangeName;
    @Value("${rabbitmq.queue-name}")
    private String queueName;
    @Value("${rabbitmq.route-key}")
    private String routeKey;
    @Bean()
    public Queue queue1(){
        return new Queue("queue1");
    }
    @Bean()
    public Queue queue2(){
        return new Queue("queue2");
    }
    @Bean(value="${rabbitmq.exchange-name}")
    public CustomExchange initExchange(){
        Map<String,Object>args=new HashMap<>();
        return new CustomExchange(exchangeName,"direct",true,false,args);
    }
    @Bean
    public Binding binding1(Queue queue1,CustomExchange customExchange){
        return BindingBuilder.bind(queue1).to(customExchange).with(routeKey).and(customExchange.getArguments());
    }
    @Bean
    public Binding binding2(Queue queue2,CustomExchange customExchange){
        return BindingBuilder.bind(queue2).to(customExchange).with(routeKey).and(customExchange.getArguments());
    }



    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
