package com.github.linyuzai.arkevent.mq.impl.filter.condition;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkMqEvent extends ArkEvent {

    default long expiration() {
        return -1;
    }
}
