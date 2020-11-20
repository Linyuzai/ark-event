package com.github.linyuzai.arkevent.exception;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class ArkEventException extends RuntimeException {

    private ArkEvent event;
    private ArkEventSubscriber subscriber;
    private ArkEventPublishStrategy strategy;

    public ArkEventException(Throwable cause, ArkEvent event, ArkEventSubscriber subscriber, ArkEventPublishStrategy strategy) {
        super(cause);
        this.event = event;
        this.subscriber = subscriber;
        this.strategy = strategy;
    }

    public ArkEventException() {
    }

    public ArkEventException(String message) {
        super(message);
    }

    public ArkEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArkEventException(Throwable cause) {
        super(cause);
    }

    public ArkEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void setEvent(ArkEvent event) {
        this.event = event;
    }

    public ArkEvent getEvent() {
        return event;
    }

    public void setSubscriber(ArkEventSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ArkEventSubscriber getSubscriber() {
        return subscriber;
    }

    public void setStrategy(ArkEventPublishStrategy strategy) {
        this.strategy = strategy;
    }

    public ArkEventPublishStrategy getStrategy() {
        return strategy;
    }
}
