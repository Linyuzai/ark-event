package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

import java.util.Map;

public interface ArkEventSubscriber extends Order {

    void onSubscribe(ArkEvent event, Map<Object, Object> args) throws Throwable;
}
