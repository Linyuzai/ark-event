package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.Order;

public interface ArkEventPublishSorter extends Order {

    boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args);
}
