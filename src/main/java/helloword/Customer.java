package helloword;

import com.rabbitmq.client.*;
import org.junit.Test;
import util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 直连模式·消费方
 *
 * @author 郑超
 * @create 2021/5/28
 */
public class Customer {

    // 消费消息
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        // 创建通道
        assert connection != null;
        Channel channel = connection.createChannel();
        // 通道绑定队列
        channel.queueDeclare("hello", false, false, false, null);
        // 消费消息
        // s 消费那个队列的消息
        // consumer 开启消息的自动确认机制
        // 消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            // body 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
            }
        });

//        RabbitMQUtils.close(channel, connection);
    }

}
