package com.github.linyuzai.arkevent.sample1;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.core.impl.filter.condition.type.OnEventType;
import com.github.linyuzai.arkevent.samplevent.SampleArkMqEvent;
import org.springframework.stereotype.Component;

@Component
@OnEventType(SampleArkMqEvent.class)
public class SampleArkMqEventSubscriber implements ArkEventSubscriber {

    @Override
    public void onSubscribe(ArkEvent event, Object... args) throws Throwable {
        System.out.println(event);
    }
}
