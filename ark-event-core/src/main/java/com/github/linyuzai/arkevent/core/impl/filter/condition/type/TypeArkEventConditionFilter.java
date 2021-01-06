package com.github.linyuzai.arkevent.core.impl.filter.condition.type;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.Map;

public class TypeArkEventConditionFilter implements ArkEventConditionFilter {

    private Class<?>[] classes;

    private boolean inherited;

    public TypeArkEventConditionFilter(Class<?>[] classes, boolean inherited) {
        this.classes = classes;
        this.inherited = inherited;
    }

    public Class<?>[] getClasses() {
        return classes;
    }

    public void setClasses(Class<?>[] classes) {
        this.classes = classes;
    }

    public boolean isInherited() {
        return inherited;
    }

    public void setInherited(boolean inherited) {
        this.inherited = inherited;
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) {
        Class<?> target = event.getClass();
        if (inherited) {
            for (Class<?> c : classes) {
                if (c.isAssignableFrom(target)) {
                    return true;
                }
            }
            return false;
        } else {
            for (Class<?> c : classes) {
                if (target == c) {
                    return true;
                }
            }
        }
        return false;
    }
}
