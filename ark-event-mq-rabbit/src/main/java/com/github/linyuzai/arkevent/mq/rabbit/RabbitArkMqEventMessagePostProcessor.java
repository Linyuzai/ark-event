package com.github.linyuzai.arkevent.mq.rabbit;

import org.springframework.amqp.core.Message;

import java.util.Map;

public interface RabbitArkMqEventMessagePostProcessor {

    void postMessage(Message message, Object event, Map<Object, Object> args);
}
