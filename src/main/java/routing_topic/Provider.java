package routing_topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 郑超
 * @create 2021/5/29
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics", "topic");
        String routingKey = "user.save";
        channel.basicPublish("topics", routingKey, null,
                ("动态路由模型，routingKey[" + routingKey + "]").getBytes());
        RabbitMQUtils.close(channel, connection);
    }
}
