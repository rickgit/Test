package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxjavaTest {
    public static void main(String[] args) {
        new RxjavaTest().testScheduler();
    }
    @Test
    public void testScheduler(){
        Flowable.just("").observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(Thread.currentThread());
            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
