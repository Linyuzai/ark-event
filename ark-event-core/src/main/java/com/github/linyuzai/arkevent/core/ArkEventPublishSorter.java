package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

import java.util.Map;

public interface ArkEventPublishSorter extends Order {

    boolean highOrder(ArkEventSubscriber subscriber, ArkEvent event, Map<Object, Object> args);
}
