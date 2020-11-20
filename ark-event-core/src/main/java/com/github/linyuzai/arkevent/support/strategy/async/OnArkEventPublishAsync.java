package com.github.linyuzai.arkevent.support.strategy.async;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnArkEventPublishAsync {
}
