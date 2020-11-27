package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.ArkOrder;

public interface ArkEventExceptionHandler {

    void handle(ArkEventException ex);

    interface Adapter extends ArkOrder {

        ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber);
    }
}
