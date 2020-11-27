package com.github.linyuzai.arkevent.basic;

public interface ArkEventDispatcher {

    void dispatch(ArkEvent event, Object... args);
}
