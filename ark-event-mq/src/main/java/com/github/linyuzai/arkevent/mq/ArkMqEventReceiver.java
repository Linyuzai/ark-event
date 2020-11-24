package com.github.linyuzai.arkevent.mq;

public interface ArkMqEventReceiver {

    void receive(String event) throws Throwable;
}
