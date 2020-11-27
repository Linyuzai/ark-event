package com.github.linyuzai.arkevent.remote;

public interface ArkEventSender<T> {

    void send(T event) throws Throwable;
}
