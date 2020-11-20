package com.github.linyuzai.arkevent.impl;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class DefaultArkEventExceptionHandler implements ArkEventExceptionHandler {

    @Override
    public void handle(ArkEventException ex) {
        throw ex;
    }
}
