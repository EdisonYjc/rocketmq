package com.edison.demo01.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import java.io.UnsupportedEncodingException;

/**
 * Producer，发送顺序消息
 * @author edison
 * @version 1.0
 * @create 2021-02-01 10:53
 **/
public class Producer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest","TagA",("Hello RocketMQ"+i).getBytes("UTF-8"));
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
