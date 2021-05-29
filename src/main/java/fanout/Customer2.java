package fanout;

import com.rabbitmq.client.*;
import util.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 郑超
 * @create 2021/5/29
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道绑定交换机
        channel.exchangeDeclare("logs", "fanout");
        // 临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queue, "logs", "");
        channel.basicConsume(queue, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2：" + new String(body));
            }
        });
    }

}
