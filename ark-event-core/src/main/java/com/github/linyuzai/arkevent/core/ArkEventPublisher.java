package com.github.linyuzai.arkevent.core;

public interface ArkEventPublisher {

    void publish(ArkEvent event, Object... args);
}
