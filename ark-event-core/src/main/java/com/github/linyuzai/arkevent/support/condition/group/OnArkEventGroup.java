package com.github.linyuzai.arkevent.support.condition.group;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnArkEventGroup {

    String[] value();

    boolean forceGroupCondition() default false;
}
