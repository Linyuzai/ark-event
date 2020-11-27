package com.github.linyuzai.arkevent.remote;

public interface ArkEventReceiver<T> {

    void receive(T event) throws Throwable;
}
