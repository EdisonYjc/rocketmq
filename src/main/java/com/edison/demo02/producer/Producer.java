package com.edison.demo02.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import java.util.List;

/**
 * 例：单个节点（Producer端1个、Consumer端1个）
 * @author edison
 * @version 1.0
 * @create 2021-02-01 13:54
 **/
public class Producer {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("order_Producer");
            // 设置多个 namesrv 用 “;” 分隔
            // 例：192.168.100.145:9876;192.168.100.146:9876;192.168.100.149:9876;192.168.100.239:9876
            producer.setNamesrvAddr("127.0.0.1:9876");
            producer.start();
            // String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD",
            // "TagE" };
            for (int i = 1; i <= 5; i++) {
                Message msg = new Message("TopicOrderTest", "order_1", "KEY" + i, ("order_1 " + i).getBytes());
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 0);
                System.out.println(sendResult);
            }
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
