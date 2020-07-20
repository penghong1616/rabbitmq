package com.ph.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqProduct {
    @Value("${rabbitmq.exchange-name}")
    private String exchangeKey;
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMessage(String routeKey,Object obj){
        amqpTemplate.convertAndSend(exchangeKey,routeKey,obj);
    }
}
