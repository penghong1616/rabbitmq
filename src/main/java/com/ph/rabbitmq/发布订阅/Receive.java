package com.ph.rabbitmq.发布订阅;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Receive {
    private static String QUEUE_NAME_A="发布订阅a";
    private static String QUEUE_NAME_B="发布订阅b";
    public static void main(String[] args) {
        Connection connection= ConnectionUtil.getConnection();
        try {
            Channel channel=connection.createChannel();
            channel.queueDeclare(QUEUE_NAME_A, false, false, false, null);
            channel.queueDeclare(QUEUE_NAME_B, false, false, false, null);
            channel.queueBind(QUEUE_NAME_A,"发布订阅","发布订阅");
            channel.queueBind(QUEUE_NAME_B,"发布订阅","发布订阅");
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
            channel.basicConsume(QUEUE_NAME_A,true,consumer_a);
            channel.basicConsume(QUEUE_NAME_B,true,consumer_b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
