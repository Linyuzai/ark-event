package com.github.linyuzai.arkevent.core;

import java.util.Map;

public interface ArkEventPublisher {

    void publish(Object event, Map<Object, Object> args);
}
