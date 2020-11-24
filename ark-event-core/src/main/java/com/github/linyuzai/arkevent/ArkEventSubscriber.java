package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.support.ArkOrder;

public interface ArkEventSubscriber extends ArkOrder {

    void onSubscribe(ArkEvent event, Object... args) throws Throwable;
}
