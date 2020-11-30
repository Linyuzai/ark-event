package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.Order;

public interface ArkEventPublishStrategy {

    void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args);

    interface Adapter extends Order {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
