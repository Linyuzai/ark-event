package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.autoconfigure.EnableArkEvent;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ArkMqEventAutoConfiguration.class,
        ArkMqEventAutoConfiguration.RabbitArkMqEventAutoConfiguration.class,
        ArkMqEventAutoConfiguration.FinalAutoConfiguration.class})
@EnableArkEvent
public @interface EnableArkMqEvent {

}
