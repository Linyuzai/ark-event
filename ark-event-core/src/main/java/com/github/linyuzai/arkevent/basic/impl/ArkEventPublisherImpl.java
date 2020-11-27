package com.github.linyuzai.arkevent.basic.impl;

import com.github.linyuzai.arkevent.basic.*;

public class ArkEventPublisherImpl implements ArkEventPublisher {

    private ArkEventPublishStrategy strategy;

    private ArkEventSubscriber subscriber;

    private ArkEventExceptionHandler handler;

    public ArkEventPublisherImpl(ArkEventSubscriber subscriber, ArkEventPublishStrategy strategy, ArkEventExceptionHandler handler) {
        this.subscriber = subscriber;
        this.strategy = strategy;
        this.handler = handler;
    }

    @Override
    public void publish(ArkEvent event, Object... args) {
        strategy.execute(subscriber, handler, event, args);
    }

    public ArkEventPublishStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ArkEventPublishStrategy strategy) {
        this.strategy = strategy;
    }

    public ArkEventSubscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(ArkEventSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ArkEventExceptionHandler getHandler() {
        return handler;
    }

    public void setHandler(ArkEventExceptionHandler handler) {
        this.handler = handler;
    }
}
