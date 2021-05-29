/**
 * 和routing_direct相比，topic可以让队列在绑定路由key时使用通配符
 * 该模型的路由key一般都是由一个或多个单词组成，多个单词用"."分割
 *
 * 符号* 匹配一个：
 *          *.orange.* 匹配三个，第二个是orange
 *          *.*.rabbit 匹配三个，第三个是orange
 * 符号# 匹配多个：
 *           lazy.# 第一个是lazy，匹配多个
 *
 * @author 郑超
 * @create 2021/5/29
 */
package routing_topic;