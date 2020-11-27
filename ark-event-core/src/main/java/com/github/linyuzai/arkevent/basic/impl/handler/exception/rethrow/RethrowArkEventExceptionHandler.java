package com.github.linyuzai.arkevent.basic.impl.handler.exception.rethrow;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventException;

public class RethrowArkEventExceptionHandler implements ArkEventExceptionHandler {

    @Override
    public void handle(ArkEventException ex) {
        throw ex;
    }
}
