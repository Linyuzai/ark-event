package com.github.linyuzai.arkevent.core;

public interface ArkEventDispatcher {

    void dispatch(ArkEvent event, Object... args);
}
