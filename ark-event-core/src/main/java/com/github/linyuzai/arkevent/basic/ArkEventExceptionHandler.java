package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.Order;

public interface ArkEventExceptionHandler {

    void handle(ArkEventException ex);

    interface Adapter extends Order {

        ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber);
    }
}
