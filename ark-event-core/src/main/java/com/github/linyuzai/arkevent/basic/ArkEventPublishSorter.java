package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkOrder;

public interface ArkEventPublishSorter extends ArkOrder {

    boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args);
}
