package com.github.linyuzai.arkevent.support.filter.condition.type;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnArkEventType {

    Class<?>[] value();

    boolean inherited() default false;
}
