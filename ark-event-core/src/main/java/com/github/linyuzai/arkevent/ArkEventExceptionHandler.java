package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.exception.ArkEventException;
import com.github.linyuzai.arkevent.support.ArkOrder;

public interface ArkEventExceptionHandler {

    void handle(ArkEventException ex);

    interface Adapter extends ArkOrder {

        ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber);
    }
}
