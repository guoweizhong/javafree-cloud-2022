package com.javafree.cloud.demos.rocketmq.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RocketmqConsumerConfiguration {
    @Value("${apache.rocketmq.name-server}")
    private String namesrvAddr;

    @Value("${apache.rocketmq.topic}")
    private String springTopic;

    @Value("${apache.rocketmq.consumer.pushConsumer}")
    private String consumerGroup;


    /**
     * rocket mq 消息的接收：
     * 1. 创建消息消费者，指定消息者所属的组名
     * 2. 指定Name server 的地址
     * 3. 指定消息者订阅的主题和标签
     * 4. 设置回调函数，编写处理消息的方法
     * 5. 启动消息消费者
     *
     */

    @Bean
    public DefaultMQPushConsumer pushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        consumer.setNamesrvAddr(namesrvAddr);
        try {

            // 订阅PushTopic下Tag为push的消息,都订阅消息
            consumer.subscribe(springTopic, "push");

            // 程序第一次启动从消息队列头获取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //可以修改每次消费消息的数量，默认设置是每次消费一条
            consumer.setConsumeMessageBatchMaxSize(1);

            //在此监听中消费信息，并返回消费的状态信息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {

                // 会把不同的消息分别放置到不同的队列中
                for (Message msg : msgs) {
                    System.out.println("接收到了消息：" + new String(msg.getBody()));
                    log.info("接收到了消息：" + new String(msg.getBody()));
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });

            consumer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }
}
