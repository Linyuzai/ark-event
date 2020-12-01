package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

public interface ArkEventExceptionHandler {

    void handle(ArkEventException ex);

    interface Adapter extends Order {

        ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber);
    }
}
