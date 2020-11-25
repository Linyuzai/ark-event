package com.github.linyuzai.arkevent.mq.rabbit;

import org.springframework.amqp.core.TopicExchange;

import java.util.Map;

public class RabbitArkMqEventTopicExchange extends TopicExchange {

    public RabbitArkMqEventTopicExchange(String name) {
        super(name);
    }

    public RabbitArkMqEventTopicExchange(String name, boolean durable, boolean autoDelete) {
        super(name, durable, autoDelete);
    }

    public RabbitArkMqEventTopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }
}
