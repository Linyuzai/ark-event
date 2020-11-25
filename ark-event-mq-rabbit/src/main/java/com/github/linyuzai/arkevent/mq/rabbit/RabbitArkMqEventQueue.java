package com.github.linyuzai.arkevent.mq.rabbit;

import org.springframework.amqp.core.Queue;

import java.util.Map;

public class RabbitArkMqEventQueue extends Queue {
    public RabbitArkMqEventQueue(String name) {
        super(name);
    }

    public RabbitArkMqEventQueue(String name, boolean durable) {
        super(name, durable);
    }

    public RabbitArkMqEventQueue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
        super(name, durable, exclusive, autoDelete);
    }

    public RabbitArkMqEventQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, exclusive, autoDelete, arguments);
    }
}
