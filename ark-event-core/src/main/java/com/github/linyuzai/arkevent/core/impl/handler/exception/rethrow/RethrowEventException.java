package com.github.linyuzai.arkevent.core.impl.handler.exception.rethrow;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RethrowEventException {
}
