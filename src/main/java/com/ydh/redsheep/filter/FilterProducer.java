package com.ydh.redsheep.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: 标签、过滤器
 * @author: yangdehong
 * @version: 2018/6/18.
 */
public class FilterProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filter");
        producer.setNamesrvAddr("192.168.1.105:9876");
        producer.setInstanceName("producer");
        producer.start();

        for (int i=0; i<100; i++) {
            Message msg = new Message("TopicTest",
                    "tag",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // Set some properties.
            msg.putUserProperty("a", String.valueOf(i));

            SendResult sendResult = producer.send(msg);
        }

        producer.shutdown();
    }

}
