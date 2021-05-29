package routing_direct;

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
        // direct路由模式（固定写死）
        channel.exchangeDeclare("logs_direct", "direct");
        String routingKey = "info";// 路由key
        channel.basicPublish("logs_direct", routingKey, null,
                ("这是direct模型发布的基于【" + routingKey + "】").getBytes());
        RabbitMQUtils.close(channel, connection);
    }
}
