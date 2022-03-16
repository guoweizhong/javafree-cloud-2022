package com.javafree.cloud.demos.rocketmq.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class RocketmqService {
    @Autowired
    private DefaultMQProducer defaultMQProducer;

    // 使用application.properties里定义的topic属性
    @Value("${apache.rocketmq.topic}")
    private String springTopic;

    private AtomicLong id =new AtomicLong(0);

    @Scheduled(fixedDelayString = "${consumer.auto.test.interval:5000}")
    public String prepareSend() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return send();
    }

    public String send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String sentText = "rocketmq message: msg id="+id.addAndGet(1);

        Message message = new Message(springTopic,"push", sentText.getBytes());
        message.putUserProperty("traceID","1234567");

        SendResult sendResult = defaultMQProducer.send(message);

        System.out.println("消息id:"+id.get()+","+"发送状态:"+sendResult.getSendStatus());

        log.info("消息id:"+id.get()+","+"发送状态:"+sendResult.getSendStatus());

        String msgid=sendResult.getMsgId();
        defaultMQProducer.shutdown();
        return msgid;
    }
}
