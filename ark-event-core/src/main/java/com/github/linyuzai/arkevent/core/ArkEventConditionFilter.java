package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

import java.util.Map;

public interface ArkEventConditionFilter extends Order {

    boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Map<Object, Object> args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
