package com.github.linyuzai.arkevent.core;

public class ArkEventException extends RuntimeException {

    private ArkEventSubscriber subscriber;
    private ArkEventPublishStrategy strategy;
    private ArkEvent event;
    private Object[] args;

    public ArkEventException(Throwable cause, ArkEventSubscriber subscriber, ArkEventPublishStrategy strategy, ArkEvent event, Object... args) {
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

    public void setEvent(ArkEvent event) {
        this.event = event;
    }

    public ArkEvent getEvent() {
        return event;
    }

    public void setArgs(Object... args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
