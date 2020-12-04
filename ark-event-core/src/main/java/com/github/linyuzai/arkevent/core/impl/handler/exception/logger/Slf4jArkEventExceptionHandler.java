package com.github.linyuzai.arkevent.core.impl.handler.exception.logger;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Slf4jArkEventExceptionHandler implements ArkEventExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(Slf4jArkEventExceptionHandler.class);

    private final Logger logger;

    public Slf4jArkEventExceptionHandler() {
        this(log);
    }

    public Slf4jArkEventExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void handle(ArkEventException ex) {
        logger.error("Event: " + ex.getEvent() + ", " +
                "Args: " + ex.getArgs() + ", " +
                "Subscriber: " + ex.getSubscriber() + ", " +
                "Strategy: " + ex.getStrategy(), ex);
    }
}
