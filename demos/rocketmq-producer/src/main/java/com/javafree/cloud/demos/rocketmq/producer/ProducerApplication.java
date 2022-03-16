package com.javafree.cloud.demos.rocketmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class,args);
    }

    /**
     *  rocketmq 发送消息步骤：
     *  1. 创建消息生产者，并设置生产组名
     *  2. 为生产者设置nameserver地址
     *  3. 启动生产者
     *  4. 构建消息对象，主要是设置消息的主题，标签，内容
     *  5. 发送消息
     *  6. 关闭生产者
     */
}
