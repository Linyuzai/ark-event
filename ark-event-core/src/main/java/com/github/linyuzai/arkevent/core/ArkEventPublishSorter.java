package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

public interface ArkEventPublishSorter extends Order {

    boolean highOrder(ArkEventSubscriber subscriber);
}
