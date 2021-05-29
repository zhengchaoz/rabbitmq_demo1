package workqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import util.RabbitMQUtils;

import java.io.IOException;

/**
 * 生产方
 *
 * @author 郑超
 * @create 2021/5/28
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 10; i++)
            channel.basicPublish("", "work", null, ("hello work queue " + i).getBytes());
        RabbitMQUtils.close(channel, connection);
    }
}
