package com.github.linyuzai.arkevent.impl;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class DefaultArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        return ExceptionHandler.INSTANCE;
    }

    public static class ExceptionHandler implements ArkEventExceptionHandler {

        static final ArkEventExceptionHandler INSTANCE = new ExceptionHandler();

        @Override
        public void handle(ArkEventException ex) {
            throw ex;
        }
    }
}
