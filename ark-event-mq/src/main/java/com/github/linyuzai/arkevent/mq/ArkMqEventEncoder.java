package com.github.linyuzai.arkevent.mq;

public interface ArkMqEventEncoder {

    Object encode(Object event) throws Throwable;
}
