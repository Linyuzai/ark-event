package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkOrder;

public interface ArkEventPublishStrategy {

    void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args);

    interface Adapter extends ArkOrder {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
