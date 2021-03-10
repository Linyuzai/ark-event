package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.autoconfigure.selector.ArkEventConfigurationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ArkEventConfigurationSelector.class, ArkEventRxJava3AutoConfiguration.class})
public @interface EnableArkEvent {

    boolean mq() default false;

    boolean transaction() default false;
}
