package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.exception.ArkEventException;

public interface ArkEventExceptionHandler {

    void handle(ArkEventException ex);

    interface Adapter {

        ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber);
    }
}
