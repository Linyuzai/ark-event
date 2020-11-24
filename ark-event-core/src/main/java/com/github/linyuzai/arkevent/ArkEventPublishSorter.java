package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.support.ArkOrder;

public interface ArkEventPublishSorter extends ArkOrder {

    int sortOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args);
}
