package com.github.linyuzai.arkevent.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ArkEventAutoConfiguration.class,
        ArkEventTransactionAutoConfiguration.class,
        ArkMqEventAutoConfiguration.class})
public @interface EnableArkEvent {

}
