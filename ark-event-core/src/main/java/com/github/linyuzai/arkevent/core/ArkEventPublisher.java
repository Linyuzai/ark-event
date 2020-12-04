package com.github.linyuzai.arkevent.core;

import java.util.Map;

public interface ArkEventPublisher {

    void publish(ArkEvent event, Map<Object, Object> args);
}
