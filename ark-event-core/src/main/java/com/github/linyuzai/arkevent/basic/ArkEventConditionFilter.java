package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.Order;

public interface ArkEventConditionFilter extends Order {

    boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
