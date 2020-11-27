package com.github.linyuzai.arkevent.basic.impl.filter.condition.group;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EventGroup {

    String[] value();
}
