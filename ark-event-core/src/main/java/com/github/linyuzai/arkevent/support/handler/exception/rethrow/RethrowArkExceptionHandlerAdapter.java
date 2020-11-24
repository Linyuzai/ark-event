package com.github.linyuzai.arkevent.support.handler.exception.rethrow;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class RethrowArkExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private RethrowArkEventExceptionHandler handler = new RethrowArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        return handler;
    }
}
