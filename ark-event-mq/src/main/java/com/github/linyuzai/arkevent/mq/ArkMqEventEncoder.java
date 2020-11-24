package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.ArkEvent;

public interface ArkMqEventEncoder {

    String encode(ArkEvent event) throws Throwable;
}
