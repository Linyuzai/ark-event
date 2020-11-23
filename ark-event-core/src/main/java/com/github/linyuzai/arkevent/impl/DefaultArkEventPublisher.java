package com.github.linyuzai.arkevent.impl;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublisher;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;

public class DefaultArkEventPublisher implements ArkEventPublisher {

    private ArkEventPublishStrategy strategy;

    private ArkEventSubscriber subscriber;

    private ArkEventExceptionHandler handler;

    public DefaultArkEventPublisher(ArkEventSubscriber subscriber, ArkEventPublishStrategy strategy, ArkEventExceptionHandler handler) {
        this.subscriber = subscriber;
        this.strategy = strategy;
        this.handler = handler;
    }

    @Override
    public void publish(ArkEvent event, Object... args) {
        strategy.execute(subscriber, handler, event, args);
    }
}
