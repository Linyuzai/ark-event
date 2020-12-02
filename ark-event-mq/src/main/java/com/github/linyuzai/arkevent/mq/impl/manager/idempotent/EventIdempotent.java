package com.github.linyuzai.arkevent.mq.impl.manager.idempotent;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EventIdempotent {
}
