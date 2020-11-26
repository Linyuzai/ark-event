package com.github.linyuzai.arkevent.support.handler.exception.rethrow;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RethrowEventException {
}
