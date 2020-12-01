package com.github.linyuzai.arkevent.mq.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArkMqEventExceptionHandler implements ArkEventExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(ArkMqEventExceptionHandler.class);

    private ArkEventExceptionHandler loggerHandler;

    public ArkMqEventExceptionHandler() {
        this(new Slf4jArkEventExceptionHandler(log));
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
