package com.github.linyuzai.arkevent.basic;

public interface ArkEventPublisher {

    void publish(ArkEvent event, Object... args);
}
