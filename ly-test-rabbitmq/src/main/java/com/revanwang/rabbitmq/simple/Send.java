package com.revanwang.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.revanwang.rabbitmq.util.RevanRabbitConnection;


/**
 * 生成者
 */
public class Send {

    //定义队列
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = RevanRabbitConnection.getConnection();
        // 从连接中创建通道，使用通道才能完成消息相关的操作
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,
                false,
                false,
                false,
                null);
        // 消息内容
        String msg = "Hello World";
        // 向指定的队列中发送消息
        channel.basicPublish("",
                QUEUE_NAME,
                null,
                msg.getBytes());
        //关闭通道和连接
        channel.close();
        connection.close();
    }

}
