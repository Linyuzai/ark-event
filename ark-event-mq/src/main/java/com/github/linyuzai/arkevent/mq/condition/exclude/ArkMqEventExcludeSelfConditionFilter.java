package com.github.linyuzai.arkevent.mq.condition.exclude;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventFromAny;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ArkMqEventExcludeSelfConditionFilter implements ArkEventConditionFilter {

    @Override
    public boolean filter(ArkEvent arkEvent, Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof ArkEventFromAny).count();
        return count == 0;
    }
}
