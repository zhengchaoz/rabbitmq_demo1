/**
 * 路由，订阅模型-Direct直连，流程如下：
 * 队列与交换机的绑定，不能是任意绑定了，而是要制定一个路由key（Routingkey）
 * 消息发送方在向Exchange发送消息时，也必须指定消息的路由key
 * 交换机不再把消息交给每一个绑定的队列，而是根据消息的路由key进行判断
 * ，只有队列的路由key和消息的路由key一致，才会接受消息
 *
 * @author 郑超
 * @create 2021/5/29
 */
package routing_direct;