package com.github.linyuzai.arkevent.core;

import java.util.Map;

public class ArkEventException extends RuntimeException {

    private ArkEventSubscriber subscriber;
    private ArkEventPublishStrategy strategy;
    private Object event;
    private Map<Object, Object> args;

    public ArkEventException(Throwable cause, Map<Object, Object> args) {
        super(cause);
        this.args = args;
    }

    public ArkEventException(Throwable cause, ArkEventSubscriber subscriber, ArkEventPublishStrategy strategy,
                             Object event, Map<Object, Object> args) {
        super(cause);
        this.subscriber = subscriber;
        this.strategy = strategy;
        this.event = event;
        this.args = args;
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

    public void setEvent(Object event) {
        this.event = event;
    }

    public Object getEvent() {
        return event;
    }

    public void setArgs(Map<Object, Object> args) {
        this.args = args;
    }

    public Map<Object, Object> getArgs() {
        return args;
    }
}
