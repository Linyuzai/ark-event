package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventArgsProcessor;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitRPC;

import java.util.Map;

public class RabbitRPCArgsProcessor implements ArkEventArgsProcessor {

    @Override
    public void process(ArkEvent event, Map<Object, Object> args) {
        if (event.getClass().isAnnotationPresent(RabbitRPC.class)) {
            args.put(RabbitRPC.class.getName(), true);
        }
    }
}
