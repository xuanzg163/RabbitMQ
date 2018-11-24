package test.RabbitMQ.base.publish.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import test.RabbitMQ.base.utils.ConnectionUtils;

/**
 * @author zhangxuan
 * @date 2018/11/24
 * @time 9:43
 */

public class Sender {
    public static final String EXCHANGE_NAME="fanout";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtils.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String msg="19900231452&123@163.com";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes("utf-8"));

        //关闭资源
        channel.close();
        connection.close();
    }
}
