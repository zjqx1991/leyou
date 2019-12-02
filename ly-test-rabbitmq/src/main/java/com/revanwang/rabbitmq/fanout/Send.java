package com.revanwang.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.revanwang.rabbitmq.util.RevanRabbitConnection;

import java.io.IOException;

/**
 * 消息生产者
 */
public class Send {
    private final static String EXCHANGE_NAME = "exchange_fanout";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = RevanRabbitConnection.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String msg = "Helle Fanout";

        channel.basicPublish(EXCHANGE_NAME,
                "",
                null,
                msg.getBytes());

        channel.close();
        connection.close();
    }
}
