package com.github.linyuzai.arkevent.support.filter.condition.group.include;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnArkEventGroupInclude {

    String[] value();
}