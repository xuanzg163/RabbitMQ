package test.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息发送方
 * @author zhangxuan
 * @date 2018/11/23
 * @time 11:17
 */

public class Sender {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{

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

        //声明消息内容
        String msg = "hello rabbit";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes("utf-8"));

        //关闭资源
        channel.close();
        connection.close();
    }
}
