package com.javafree.cloud.demos.rocketmq.producer.controller;

import com.javafree.cloud.demos.rocketmq.producer.service.RocketmqService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private RocketmqService rocketmqService;

    @ResponseBody
    @RequestMapping("/send")
    public String sendMessage() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return rocketmqService.prepareSend();
    }
}
