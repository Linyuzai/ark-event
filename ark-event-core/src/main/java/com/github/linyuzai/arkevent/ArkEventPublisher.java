package com.github.linyuzai.arkevent;

public interface ArkEventPublisher {

    void publish(ArkEvent event, Object... args);
}
