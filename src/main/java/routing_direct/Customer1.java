package routing_direct;

import com.rabbitmq.client.*;
import util.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 郑超
 * @create 2021/5/29
 */
public class Customer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道声明交换机以及交换的类型
        channel.exchangeDeclare("logs_direct", "direct");
        String queue = channel.queueDeclare().getQueue();// 临时队列名
        // 基于路由key绑定队列和交换机
        channel.queueBind(queue, "logs_direct", "error");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1：" + new String(body));
            }
        });
    }
}
