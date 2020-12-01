package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

public interface ArkEventPublishSorter extends Order {

    boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args);
}
