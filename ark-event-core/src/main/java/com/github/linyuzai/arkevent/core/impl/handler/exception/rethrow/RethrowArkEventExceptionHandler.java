package com.github.linyuzai.arkevent.core.impl.handler.exception.rethrow;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;

public class RethrowArkEventExceptionHandler implements ArkEventExceptionHandler {

    @Override
    public void handle(ArkEventException ex) {
        throw ex;
    }
}
