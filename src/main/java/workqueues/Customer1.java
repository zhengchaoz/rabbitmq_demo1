package workqueues;

import com.rabbitmq.client.*;
import util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消费方1
 *
 * @author 郑超
 * @create 2021/5/28
 */
public class Customer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);// 每一次只能消费一个消息
        channel.queueDeclare("work", true, false, false, null);
        // 参数2：消息自动确认 true 消费者自动向rabbitmq确认消息消费——即队列中对应的消息数量立刻清除，不管该消费者是否真实消费完成
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 手动确认 参数1：手动确认消息标识 参数2：false 每次确认一个
                // 意味着只有每个消息真实消费完，才会通知队列清除
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
