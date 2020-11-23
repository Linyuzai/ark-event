package com.github.linyuzai.arkevent;

public interface ArkEventPublishStrategy {

    void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args);

    interface Adapter {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
