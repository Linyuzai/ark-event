package com.github.linyuzai.arkevent.rxjava3.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.rxjava3.RxJava3FlowableArkEventSubscriber;
import com.github.linyuzai.arkevent.support.ArkEventArgsPair;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import java.util.Map;

public class RxJava3FlowableArkEventPublishStrategy implements ArkEventPublishStrategy,
        FlowableOnSubscribe<ArkEventArgsPair>, Consumer<ArkEventArgsPair> {

    private RxJava3FlowableArkEventSubscriber subscriber;

    private FlowableEmitter<ArkEventArgsPair> emitter;

    public RxJava3FlowableArkEventPublishStrategy(RxJava3FlowableArkEventSubscriber subscriber) {
        this.subscriber = subscriber;
        Flowable<ArkEventArgsPair> f = Flowable.create(this, subscriber.getMode());
        subscriber.flow(f);
        Disposable disposable = f.subscribe(this);
        subscriber.onDisposableCreated(disposable);
    }

    @Override
    public void apply(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) throws Throwable {
        emitter.onNext(new ArkEventArgsPair(event, args));
    }


    @Override
    public void subscribe(@NonNull FlowableEmitter<ArkEventArgsPair> emitter) throws Throwable {
        this.emitter = emitter;
    }

    @Override
    public void accept(ArkEventArgsPair pair) throws Throwable {
        subscriber.onSubscribe(pair.getEvent(), pair.getArgs());
    }
}
