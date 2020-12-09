package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MqEvent {

    boolean transaction() default false;

    long expiration() default 0;
}
