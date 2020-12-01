package com.github.linyuzai.arkevent.core.impl.handler.exception.logger;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Slf4jArkEventExceptionHandler implements ArkEventExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(Slf4jArkEventExceptionHandler.class);

    @Override
    public void handle(ArkEventException ex) {
        logger.error("Event: " + ex.getEvent() + ", " +
                "Args: " + Arrays.toString(ex.getArgs()) + ", " +
                "Subscriber: " + ex.getSubscriber() + ", " +
                "Strategy: " + ex.getStrategy(), ex);
    }
}
