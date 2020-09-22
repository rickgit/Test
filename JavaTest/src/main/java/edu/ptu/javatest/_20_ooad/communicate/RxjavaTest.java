package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxjavaTest {
    public static void main(String[] args) {
        new RxjavaTest().testScheduler();
    }
    @Test
    public void testScheduler(){
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe" + Thread.currentThread());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete" + Thread.currentThread());
            }
        };
        Consumer<String> onNext = new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("accept" + Thread.currentThread());
            }
        };
        Flowable.just("1").observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("onSubscribe" + Thread.currentThread());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext" + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete" + Thread.currentThread());
                    }
                });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
