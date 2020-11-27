package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkOrder;

public interface ArkEventSubscriber extends ArkOrder {

    void onSubscribe(ArkEvent event, Object... args) throws Throwable;
}
