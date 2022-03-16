package com.javafree.cloud.demos.rocketmq.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class RocketmqProducerConfiguration {
    @Value("${apache.rocketmq.name-server}")
    private String namesrvAddr;

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        producer.setNamesrvAddr(namesrvAddr);

        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(0);
        //producer.start();
        log.info("rocketmq producer is starting");
        return producer;
    }
}
