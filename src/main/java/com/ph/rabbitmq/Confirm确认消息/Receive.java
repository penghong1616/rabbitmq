package com.ph.rabbitmq.Confirm确认消息;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Receive {
    //队列名称
    private static final String QUEUE_NAME = "confirm";

    public static void main(String[] args)
    {
        try
        {
            //获取连接
            Connection connection = ConnectionUtil.getConnection();
            //从连接中获取一个通道
            Channel channel = connection.createChannel();
            //声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //定义消费者
            DefaultConsumer consumer = new DefaultConsumer(channel)
            {
                //当消息到达时执行回调方法
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException
                {
                    String message = new String(body, "utf-8");
                    System.out.println("[Receive]：" + message);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            //监听队列
            channel.basicConsume(QUEUE_NAME, false, consumer);
        }
        catch (IOException | ShutdownSignalException | ConsumerCancelledException e)
        {
            e.printStackTrace();
        }
    }
}
