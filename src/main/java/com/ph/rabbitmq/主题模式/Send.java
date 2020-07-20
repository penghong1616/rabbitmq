package com.ph.rabbitmq.主题模式;

import com.ph.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    Channel channel;
    @Before
    public void init() throws IOException {
        Connection connection= ConnectionUtil.getConnection();
        channel=connection.createChannel();
    }
    @Test
    public void send() throws IOException, TimeoutException {
        channel.exchangeDeclare("主题模式","topic");
        String message="主题模式下发送消息";
        channel.basicPublish("主题模式","p.a",null,message.getBytes("utf-8"));
        channel.close();
    }
}
