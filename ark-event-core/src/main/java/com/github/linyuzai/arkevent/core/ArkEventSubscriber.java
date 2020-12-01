package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

public interface ArkEventSubscriber extends Order {

    void onSubscribe(ArkEvent event, Object... args) throws Throwable;
}
