package com.github.linyuzai.arkevent.mq.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
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
        if (ArkEventPlugin.isMqTransaction(ex.getArgs())) {
            throw ex;
        } else {
            handleMqException(ex);
        }
    }

    public void handleMqException(ArkEventException ex) {
        loggerHandler.handle(ex);
    }
}
