package com.github.linyuzai.arkevent.support;

import java.util.Map;

public class ArkEventArgsPair {

    private Object event;

    private Map<Object, Object> args;

    public ArkEventArgsPair() {
    }

    public ArkEventArgsPair(Object event, Map<Object, Object> args) {
        this.event = event;
        this.args = args;
    }

    public Object getEvent() {
        return event;
    }

    public void setEvent(Object event) {
        this.event = event;
    }

    public Map<Object, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<Object, Object> args) {
        this.args = args;
    }
}
