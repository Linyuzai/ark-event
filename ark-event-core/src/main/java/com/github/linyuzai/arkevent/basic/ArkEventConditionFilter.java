package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkOrder;

public interface ArkEventConditionFilter extends ArkOrder {

    boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
