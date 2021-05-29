package helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 直连模式·生产方
 *
 * @author 郑超
 * @create 2021/5/27
 */
public class Sender {

    // 生产消息
    @Test
    public void testSenderMessage() throws IOException, TimeoutException {
        // 创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接mq主机
        connectionFactory.setHost("192.168.126.128");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        // 设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        // 获取连接对象
        Connection connection = connectionFactory.newConnection();
        // 获取连接中的通道
        Channel channel = connection.createChannel();
        /*
         通道绑定对应的消息队列
         s 队列名称，如果不存在就自动创建
         b 用来定义队列的特性是否要持久化 true持久化 false不持久化 --> mq重启后队列是否还存在
         b1 是否独占队列 true独占 false不独占 --> 是否只有本次连接才能使用该队列
         b2 是否在消费完成后自动删除消息为0的队列 true自动删除 false不自动删除 --> 注意：只有在消费方close()后才会删除队列
         map 额外附加参数
        */
        channel.queueDeclare("hello", false, false, false, null);
        /*
         发布消息
         s 交换机名称
         s1 队列名称
         BasicProperties 传递消息的额外设置
         bytes 传递消息的具体内容
        */
        channel.basicPublish("", "hello", null, "hello rabbitMQ".getBytes());
        // MessageProperties.PERSISTENT_TEXT_PLAIN 将队列中的消息持久化
        // channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitMQ".getBytes());
        // 关闭通道
        channel.close();
        // 关闭连接
        connection.close();
    }

}
