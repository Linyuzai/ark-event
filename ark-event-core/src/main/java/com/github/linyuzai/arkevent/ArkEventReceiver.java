package com.github.linyuzai.arkevent;

public interface ArkEventReceiver<T> {

    void receive(T event) throws Throwable;
}
