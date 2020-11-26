package com.github.linyuzai.arkevent.support.handler.exception.rethrow;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class RethrowArkExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private RethrowArkEventExceptionHandler handler = new RethrowArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(RethrowEventException.class)) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_RETHROW;
    }
}
