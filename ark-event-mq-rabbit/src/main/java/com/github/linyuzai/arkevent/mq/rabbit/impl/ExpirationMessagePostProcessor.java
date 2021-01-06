package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.mq.impl.filter.condition.ArkMqEvent;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.MqEvent;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessagePostProcessor;
import org.springframework.amqp.core.Message;

import java.util.Map;

public class ExpirationMessagePostProcessor implements RabbitArkMqEventMessagePostProcessor {

    @Override
    public void postMessage(Message message, Object event, Map<Object, Object> args) {
        long exp = getExpiration(event);
        if (exp > 0) {
            message.getMessageProperties().setHeader("expiration", String.valueOf(exp));
        }
    }

    private long getExpiration(Object event) {
        if (event instanceof ArkMqEvent) {
            return ((ArkMqEvent) event).expiration();
        } else {
            MqEvent mqEvent = event.getClass().getAnnotation(MqEvent.class);
            return mqEvent == null ? 0 : mqEvent.expiration();
        }
    }
}
