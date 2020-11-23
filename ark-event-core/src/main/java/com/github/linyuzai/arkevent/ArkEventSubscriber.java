package com.github.linyuzai.arkevent;

public interface ArkEventSubscriber {

    void onSubscribe(ArkEvent event, Object... args) throws Throwable;
}
