package com.ph.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.logging.Logger;

public class ConnectionUtil
{
    private static Logger logger = Logger.getLogger(String.valueOf(ConnectionUtil.class));

    public static Connection getConnection()
    {
        try
        {
            Connection connection = null;
            //定义一个连接工厂
            ConnectionFactory factory = new ConnectionFactory();
            //设置服务端地址（域名地址/ip）
            factory.setHost("node");
            //设置服务器端口号
            factory.setPort(5672);
            //设置虚拟主机(相当于数据库中的库)
            factory.setVirtualHost("test");
            //设置用户名
            factory.setUsername("ph");
            //设置密码
            factory.setPassword("123123rm@");
            connection = factory.newConnection();
            return connection;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}