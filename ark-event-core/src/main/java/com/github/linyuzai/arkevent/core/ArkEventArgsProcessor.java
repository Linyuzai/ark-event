package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.Order;

import java.util.Map;

public interface ArkEventArgsProcessor extends Order {

    void process(ArkEvent event, Map<Object, Object> args);
}
