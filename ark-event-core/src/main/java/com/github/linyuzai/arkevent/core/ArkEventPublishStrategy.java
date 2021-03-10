package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

import java.util.Map;

public interface ArkEventPublishStrategy {

    void apply(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) throws Throwable;

    interface Adapter extends Order {

        ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber);
    }
}
