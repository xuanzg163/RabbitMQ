package test.RabbitMQ.base.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 获取连接
 * @author zhangxuan
 * @date 2018/11/24
 * @time 9:44
 */
public class ConnectionUtils {

    public static Connection getConnection() throws Exception{
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
        return connectionFactory.newConnection();
    }
}
