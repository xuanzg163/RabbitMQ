package test.RabbitMQ.base;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息接收方
 * @author zhangxuan
 * @date 2018/11/23
 * @time 11:18
 */
public class Receiver {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置主机参数
        /**
         * 主机ip
         * 客户端连接端口号 5672
         * 设置操作的 virtual host
         * 设置用户名
         * 设置密码
         */
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/rabbit");
        connectionFactory.setUsername("shsxt");
        connectionFactory.setPassword("123456");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //获取通道对象
        Channel channel = connection.createChannel();

        //声明对列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String msg = new String(body,"utf-8");
                System.out.println("consumer收到的消息===="+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
