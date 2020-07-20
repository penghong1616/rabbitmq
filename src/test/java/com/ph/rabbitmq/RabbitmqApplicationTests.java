package com.ph.rabbitmq;

import com.ph.rabbitmq.config.MqProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {
    @Autowired
    private MqProduct mqProduct;
    @Value("${rabbitmq.route-key}")
    private String routeKey;
    @Test
    void contextLoads() {
    }
    @Test
    public void mqSend(){
        mqProduct.sendMessage(routeKey,"Hello,World!!!");
    }
}
