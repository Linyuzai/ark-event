package com.github.linyuzai.arkevent.rxjava3;

import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.support.ArkEventArgsPair;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;

public interface RxJava3ObservableArkEventSubscriber extends ArkEventSubscriber {

    void observe(Observable<ArkEventArgsPair> observable);

    default void onCreated(ObservableEmitter<ArkEventArgsPair> emitter, Disposable disposable) {

    }
}
