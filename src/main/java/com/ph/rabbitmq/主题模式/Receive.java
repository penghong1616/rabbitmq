package com.ph.rabbitmq.主题模式;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class Receive {
    Channel channel;
    @Before
    public void init(){
        Connection connection= ConnectionUtil.getConnection();
        try {
            channel=connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void receive() throws IOException {
        channel.exchangeDeclare("主题模式","topic");
        channel.queueDeclare("topic_a",false,false,false,null);
        channel.queueDeclare("topic_b",false,false,false,null);
        channel.queueBind("topic_a","主题模式","p.#");
        channel.queueBind("topic_b","主题模式","h.#");
        DefaultConsumer consumer_a = new DefaultConsumer(channel)
        {
            //当消息到达时执行回调方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException
            {
                String message = new String(body, "utf-8");
                System.out.println("[Receive_a]：" + message);
            }
        };
        DefaultConsumer consumer_b = new DefaultConsumer(channel)
        {
            //当消息到达时执行回调方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException
            {
                String message = new String(body, "utf-8");
                System.out.println("[Receive_b]：" + message);
            }
        };
        channel.basicConsume("topic_a",true,consumer_a);
        channel.basicConsume("topic_b",true,consumer_b);
    }
}
