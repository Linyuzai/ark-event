package com.github.linyuzai.arkevent.remote;

public interface ArkEventReceiver<T> {

    void receive(T event, Object... args) throws Throwable;
}
