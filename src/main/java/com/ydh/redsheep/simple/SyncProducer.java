package com.ydh.redsheep.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: 同步发送
 * @author: yangdehong
 * @version: 2018/6/9.
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        // 实例化一个生产组名称
        DefaultMQProducer producer = new DefaultMQProducer("simpleSyncProducer");
        // 指定名称服务器地址
        producer.setNamesrvAddr("172.16.165.182:9876");
        producer.setInstanceName("producer");
//        producer.setVipChannelEnabled(false);
        // 启动生产者
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建一个消息
            Message msg = new Message("TopicTest", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送消息给brokers
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        // 关闭生产者
        producer.shutdown();
    }

}
