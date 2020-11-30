package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.Order;

public interface ArkEventSubscriber extends Order {

    void onSubscribe(ArkEvent event, Object... args) throws Throwable;
}
