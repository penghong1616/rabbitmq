package com.ph.rabbitmq.发布订阅;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static String QUEUE_NAME="发布订阅";

    public static void main(String[] args) {
        Connection connection= ConnectionUtil.getConnection();
        try {
            Channel channel=connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,true,true,true,null);

            channel.exchangeDeclare("发布订阅","fanout");
            String message="发布订阅模式下发送消息";
            channel.basicPublish("发布订阅","发布订阅",false,null,message.getBytes("utf-8"));
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
