package com.github.linyuzai.arkevent.remote;

public interface ArkEventSender<T> {

    void send(T event, Object... args) throws Throwable;
}
