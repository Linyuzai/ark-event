package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventIdempotentManager;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessagePostProcessor;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import org.springframework.amqp.core.Message;

import java.util.Map;

public class EventIdMessagePostProcessor implements RabbitArkMqEventMessagePostProcessor {

    private ArkMqEventIdempotentManager idempotentManager;

    public EventIdMessagePostProcessor(ArkMqEventIdempotentManager idempotentManager) {
        this.idempotentManager = idempotentManager;
    }

    public ArkMqEventIdempotentManager getIdempotentManager() {
        return idempotentManager;
    }

    public void setIdempotentManager(ArkMqEventIdempotentManager idempotentManager) {
        this.idempotentManager = idempotentManager;
    }

    @Override
    public void postMessage(Message message, ArkEvent event, Map<Object, Object> args) {
        String eventId = idempotentManager.getEventId(event, args);
        message.getMessageProperties().setHeader(ArkEventPlugin.HEADER_EVENT_ID, eventId);
    }
}
