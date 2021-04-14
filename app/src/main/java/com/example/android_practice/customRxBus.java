package com.example.android_practice;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class customRxBus {
    private static volatile customRxBus rxBus;
    private final Subject<Object> subject = ReplaySubject.create().toSerialized();

    private customRxBus() {

    }

    public static customRxBus getInstance() {
        if (rxBus == null) {
            synchronized (customRxBus.class) {
                if (rxBus == null) {
                    rxBus = new customRxBus();
                }
            }
        }
        return rxBus;
    }

    public void post(Object ob) {
        subject.onNext(ob);
    }

    public <T> Observable toObservable(Class<T> eventType) {
        return subject.ofType(eventType);
    }
}
