package com.github.linyuzai.arkevent.support.condition.type;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class TypeArkEventConditionFilter implements ArkEventConditionFilter {

    private Class<?>[] classes;

    private boolean inherited;

    public TypeArkEventConditionFilter(Class<?>[] classes, boolean inherited) {
        this.classes = classes;
        this.inherited = inherited;
    }

    @Override
    public boolean filter(ArkEvent event) {
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
