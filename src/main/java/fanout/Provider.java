package fanout;

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
        // 将通道声明指定的交换机  参数二 fanout广播类型（固定写死）
        channel.exchangeDeclare("logs", "fanout");
        // 发送消息
        channel.basicPublish("logs", "", null, "fanout taye message".getBytes());
        RabbitMQUtils.close(channel, connection);
    }
}
