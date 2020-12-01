package com.github.linyuzai.arkevent.sample2;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.samplevent.SampleArkMqEvent;
import com.github.linyuzai.arkevent.core.impl.filter.condition.type.OnEventType;
import org.springframework.stereotype.Component;

@Component
@OnEventType(SampleArkMqEvent.class)
public class SampleArkMqEventSubscriber implements ArkEventSubscriber {

    @Override
    public void onSubscribe(ArkEvent event, Object... args) throws Throwable {
        System.out.println(event);
    }
}
