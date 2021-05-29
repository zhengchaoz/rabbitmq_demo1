package workqueues;

import com.rabbitmq.client.*;
import util.RabbitMQUtils;

import java.io.IOException;

/**
 * 消费方2
 *
 * @author 郑超
 * @create 2021/5/28
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2" + new String(body));
                // 手动确认 参数1：手动确认消息标识 参数2：false 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
