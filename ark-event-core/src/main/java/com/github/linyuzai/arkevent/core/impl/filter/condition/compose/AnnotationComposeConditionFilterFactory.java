package com.github.linyuzai.arkevent.core.impl.filter.condition.compose;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.lang.annotation.Annotation;
import java.util.List;

public class AnnotationComposeConditionFilterFactory extends ComposeConditionFilterFactory {

    private Class<? extends Annotation> annotation;

    public AnnotationComposeConditionFilterFactory(List<ArkEventConditionFilter.Factory> factories) {
        this(factories, EventCompose.class);
    }

    public AnnotationComposeConditionFilterFactory(List<ArkEventConditionFilter.Factory> factories,
                                                   Class<? extends Annotation> annotation) {
        super(factories);
        this.annotation = annotation;
    }

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        if (!subscriber.getClass().isAnnotationPresent(annotation)) {
            return null;
        }
        return super.getConditionFilter(subscriber);
    }
}
