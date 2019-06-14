package com.ydh.redsheep.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @description: 单向传输用于需要中等可靠性的情况，例如日志收集
 * @author: yangdehong
 * @version: 2018/6/9.
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("simpleOnewayProducer");
        producer.setNamesrvAddr("172.16.165.182:9876");
        producer.setInstanceName("producer");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);

        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
