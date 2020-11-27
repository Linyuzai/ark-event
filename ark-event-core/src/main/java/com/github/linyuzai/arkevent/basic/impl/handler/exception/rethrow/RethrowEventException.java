package com.github.linyuzai.arkevent.basic.impl.handler.exception.rethrow;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RethrowEventException {
}
