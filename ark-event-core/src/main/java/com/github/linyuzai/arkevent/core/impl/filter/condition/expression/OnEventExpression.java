package com.github.linyuzai.arkevent.core.impl.filter.condition.expression;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnEventExpression {

    String value();
}
