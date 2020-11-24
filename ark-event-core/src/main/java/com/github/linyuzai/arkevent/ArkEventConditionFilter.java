package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.support.ArkOrder;

public interface ArkEventConditionFilter extends ArkOrder {

    boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args);

    interface Factory {

        ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber);
    }
}
