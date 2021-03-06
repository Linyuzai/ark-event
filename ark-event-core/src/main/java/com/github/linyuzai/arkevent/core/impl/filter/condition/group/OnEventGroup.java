package com.github.linyuzai.arkevent.core.impl.filter.condition.group;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnEventGroup {

    String[] value();

    boolean requireGroupCondition() default false;
}
