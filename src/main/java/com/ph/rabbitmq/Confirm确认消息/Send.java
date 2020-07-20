package com.ph.rabbitmq.Confirm确认消息;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
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
            String message = "This is simple queue";
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("消息确认---"+deliveryTag);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("消息未确认---"+deliveryTag);
                }
            });
            System.out.println("[send]：" + message);

            channel.close();
            connection.close();
        }
        catch (IOException | TimeoutException e)
        {
            e.printStackTrace();
        }
    }
}
