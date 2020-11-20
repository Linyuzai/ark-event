package com.github.linyuzai.arkevent.support.condition.group.exclude;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnArkEventGroupExclude {

    String[] value();
}
