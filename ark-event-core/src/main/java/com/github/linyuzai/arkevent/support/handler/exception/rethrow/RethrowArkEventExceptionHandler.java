package com.github.linyuzai.arkevent.support.handler.exception.rethrow;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class RethrowArkEventExceptionHandler implements ArkEventExceptionHandler {

    @Override
    public void handle(ArkEventException ex) {
        throw ex;
    }
}
