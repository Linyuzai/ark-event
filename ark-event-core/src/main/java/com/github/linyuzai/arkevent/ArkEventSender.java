package com.github.linyuzai.arkevent;

public interface ArkEventSender<T> {

    void send(T event) throws Throwable;
}
