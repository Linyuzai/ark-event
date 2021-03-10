package com.github.linyuzai.arkevent.rxjava3;

import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.ArkEventArgsPair;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;

public interface RxJava3FlowableArkEventSubscriber extends ArkEventSubscriber {

    void flow(Flowable<ArkEventArgsPair> flowable);

    BackpressureStrategy getMode();

    default void onCreated(FlowableEmitter<ArkEventArgsPair> emitter, Disposable disposable) {

    }
}
