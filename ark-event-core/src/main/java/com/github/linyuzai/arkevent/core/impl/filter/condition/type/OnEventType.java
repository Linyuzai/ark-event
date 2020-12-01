package com.github.linyuzai.arkevent.core.impl.filter.condition.type;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnEventType {

    Class<?>[] value();

    boolean inherited() default false;
}
