package com.github.linyuzai.arkevent.sample3;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.samplevent.SampleArkMqEvent;
import com.github.linyuzai.arkevent.support.filter.condition.type.OnArkEventType;
import org.springframework.stereotype.Component;

@Component
@OnArkEventType(SampleArkMqEvent.class)
public class SampleArkMqEventSubscriber implements ArkEventSubscriber {

    @Override
    public void onSubscribe(ArkEvent event, Object... args) throws Throwable {
        System.out.println(event);
    }
}
