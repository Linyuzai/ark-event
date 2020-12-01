package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.core.ArkEvent;

public interface ArkMqEventDecoder {

    ArkEvent decode(Object event) throws Throwable;
}
