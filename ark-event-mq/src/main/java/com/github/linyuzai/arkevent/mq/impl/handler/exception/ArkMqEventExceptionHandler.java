package com.github.linyuzai.arkevent.mq.impl.handler.exception;

import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;

public class ArkMqEventExceptionHandler implements ArkEventExceptionHandler {

    private ArkEventExceptionHandler loggerHandler;

    public ArkMqEventExceptionHandler() {
        this(new Slf4jArkEventExceptionHandler());
    }

    public ArkMqEventExceptionHandler(ArkEventExceptionHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
    }

    @Override
    public void handle(ArkEventException ex) {
        try {
            handleMqException(ex);
        } catch (Throwable e) {
            loggerHandler.handle(ex);
        }
    }

    public void handleMqException(ArkEventException ex) throws Throwable {
        loggerHandler.handle(ex);
    }
}
