package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventArgsProcessor;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitPublisherConfirms;

import java.util.Map;

public class RabbitPublisherConfirmsArgsProcessor implements ArkEventArgsProcessor {

    @Override
    public void process(ArkEvent event, Map<Object, Object> args) {
        if (event.getClass().isAnnotationPresent(RabbitPublisherConfirms.class)) {
            args.put(RabbitPublisherConfirms.class.getName(), true);
        }
    }
}
