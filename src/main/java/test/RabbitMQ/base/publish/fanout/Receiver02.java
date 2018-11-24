package test.RabbitMQ.base.publish.fanout;

import com.rabbitmq.client.*;
import test.RabbitMQ.base.utils.ConnectionUtils;

import java.io.IOException;

/**
 * @author zhangxuan
 * @date 2018/11/24
 * @time 9:43
 */

public class Receiver02 {
    public static final String QUEUE_NAME="mail";
    public static final String EXCHANGE_NAME="fanout";
    public static void main(String[] args) throws  Exception{
        Connection connection= ConnectionUtils.getConnection();
        // 创建通道
        Channel channel=connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        // 指定队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        // 指定队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body,"utf-8");
                System.out.println("邮件消费方收到消息-->"+msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
