package com.github.linyuzai.arkevent.support.filter.condition.group;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EventGroup {

    String[] value();
}
