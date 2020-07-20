package com.ph.rabbitmq.config;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {
    @RabbitHandler
    @RabbitListener(queues="queue1")
    public void consumerMessage1(Message message) {
        System.out.println("RabbitMq消费者收到的消息"+message.toString());
        System.out.println("RabbitMq消费者收到的消息"+message.getBody().toString());
        Jackson2JsonMessageConverter jackson2JsonMessageConverter =new Jackson2JsonMessageConverter();
        Object object=jackson2JsonMessageConverter.fromMessage(message);
    }
    @RabbitListener(queues="queue2")
    public void consumerMessage2(Message message) {
        System.out.println("RabbitMq消费者收到的消息"+message.toString());
        System.out.println("RabbitMq消费者收到的消息"+message.getBody().toString());
        Jackson2JsonMessageConverter jackson2JsonMessageConverter =new Jackson2JsonMessageConverter();
        Object object=jackson2JsonMessageConverter.fromMessage(message);
    }

}
