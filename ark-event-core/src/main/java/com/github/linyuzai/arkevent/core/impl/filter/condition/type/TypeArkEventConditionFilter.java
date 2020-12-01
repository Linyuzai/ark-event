package com.github.linyuzai.arkevent.core.impl.filter.condition.type;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class TypeArkEventConditionFilter implements ArkEventConditionFilter {

    private Class<?>[] classes;

    private boolean inherited;

    public TypeArkEventConditionFilter(Class<?>[] classes, boolean inherited) {
        this.classes = classes;
        this.inherited = inherited;
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
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
