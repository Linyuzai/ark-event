package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.support.ArkOrder;

public interface ArkEventPublishStrategy {

    void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args);

    interface Adapter extends ArkOrder {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
