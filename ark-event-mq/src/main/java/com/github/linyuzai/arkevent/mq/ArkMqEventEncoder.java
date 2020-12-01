package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkMqEventEncoder {

    Object encode(ArkEvent event) throws Throwable;
}
