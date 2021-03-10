package com.github.linyuzai.arkevent.rxjava3.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.rxjava3.RxJava3ObservableArkEventSubscriber;
import com.github.linyuzai.arkevent.support.ArkEventArgsPair;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.Map;

public class RxJava3ObservableArkEventPublishStrategy implements ArkEventPublishStrategy,
        ObservableOnSubscribe<ArkEventArgsPair>, Consumer<ArkEventArgsPair> {

    private RxJava3ObservableArkEventSubscriber subscriber;

    private ObservableEmitter<ArkEventArgsPair> emitter;

    public RxJava3ObservableArkEventPublishStrategy(RxJava3ObservableArkEventSubscriber subscriber) {
        this.subscriber = subscriber;
        Observable<ArkEventArgsPair> o = Observable.create(this);
        subscriber.observe(o);
        Disposable disposable = o.subscribe(this);
        subscriber.onCreated(emitter, disposable);
    }

    @Override
    public void apply(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) throws Throwable {
        emitter.onNext(new ArkEventArgsPair(event, args));
    }


    @Override
    public void subscribe(@NonNull ObservableEmitter<ArkEventArgsPair> emitter) throws Throwable {
        this.emitter = emitter;
    }

    @Override
    public void accept(ArkEventArgsPair pair) throws Throwable {
        subscriber.onSubscribe(pair.getEvent(), pair.getArgs());
    }
}
