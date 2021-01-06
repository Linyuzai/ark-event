package com.github.linyuzai.arkevent.mq;

public interface ArkMqEventDecoder {

    Object decode(Object event) throws Throwable;
}
