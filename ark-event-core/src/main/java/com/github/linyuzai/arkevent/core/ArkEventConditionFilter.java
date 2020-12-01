package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

public interface ArkEventConditionFilter extends Order {

    boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
