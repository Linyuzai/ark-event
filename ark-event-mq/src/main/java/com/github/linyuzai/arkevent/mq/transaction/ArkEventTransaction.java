package com.github.linyuzai.arkevent.mq.transaction;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ArkEventTransaction {
}
