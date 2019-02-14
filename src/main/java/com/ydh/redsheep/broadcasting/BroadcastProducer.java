package com.ydh.redsheep.broadcasting;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: 广播正在向一个主题的所有订阅者发送一条消息。如果你想让所有的订阅者都收到关于某个话题的信息，广播是一个不错的选择。
 *  生产者
 * @author: yangdehong
 * @version: 2018/6/10.
 */
public class BroadcastProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast");
        producer.setNamesrvAddr("192.168.1.105:9876");
        producer.setInstanceName("producer");
        producer.setVipChannelEnabled(false);
        producer.start();

        for (int i = 0; i < 100; i++){
            Message msg = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }

}
