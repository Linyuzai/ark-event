package com.github.linyuzai.arkevent;

public interface ArkEventPublishStrategy {

    void publish(ArkEventSubscriber subscriber, ArkEvent event, ArkEventExceptionHandler handler);

    interface Adapter {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
