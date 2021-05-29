package util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简化RabbitMQ的连接操作
 *
 * @author 郑超
 * @create 2021/5/28
 */
public class RabbitMQUtils {

    private static final ConnectionFactory connectionFactory;

    // 只创建一次工厂对象
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.126.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
    }

    // 创建连接对象
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 关闭通道和连接
    public static void close(Channel channel, Connection connection) {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
