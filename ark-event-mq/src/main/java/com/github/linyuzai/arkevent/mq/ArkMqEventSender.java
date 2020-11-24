package com.github.linyuzai.arkevent.mq;

public interface ArkMqEventSender {

    void send(String event) throws Throwable;
}
