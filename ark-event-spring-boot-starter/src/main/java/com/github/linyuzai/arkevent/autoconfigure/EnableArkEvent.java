package com.github.linyuzai.arkevent.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ArkEventConfigurationSelector.class)
public @interface EnableArkEvent {

    boolean mq() default false;

    boolean transaction() default false;
}
