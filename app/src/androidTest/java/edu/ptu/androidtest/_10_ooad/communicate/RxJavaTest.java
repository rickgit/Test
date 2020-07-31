package edu.ptu.androidtest._10_ooad.communicate;

import android.os.Looper;
import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaTest {
    private static final String TAG = "RxJavaTest";

    @Test
    public void testTestMain() {
        Flowable.just("").map((Function<String, Object>) s -> {
            System.out.println("onSubscribe" + (Looper.myLooper() == Looper.getMainLooper()));
            return "null";
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        System.out.println("onSubscribe" + (Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("onSubscribe" + (Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onSubscribe" + (Looper.myLooper() == Looper.getMainLooper()));
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onSubscribe" + (Looper.myLooper() == Looper.getMainLooper()));
                    }
                });
        try {
            Thread.sleep(15000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubscribeOn() {
        Observable.just(1)
                .map(integer -> {
                    Log.i(TAG, "map-1:" + Thread.currentThread().getName());
                    return integer;
                })
                .subscribeOn(Schedulers.newThread())
                .map(integer -> {
                    Log.i(TAG, "map-2:" + Thread.currentThread().getName());
                    return integer;
                })
                .subscribeOn(Schedulers.io())
                .map(integer -> {
                    Log.i(TAG, "map-3:" + Thread.currentThread().getName());
                    return integer;
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.i(TAG, "subscribe:" + Thread.currentThread().getName()));
        try {
            Thread.sleep(15000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void onObserverOn(){
        Observable.just(1)
                .map(integer -> {
                    Log.i(TAG, "map-1:"+Thread.currentThread().getName());
                    return integer;
                })
                .observeOn(Schedulers.newThread())
                .map(integer -> {
                    Log.i(TAG, "map-2:"+Thread.currentThread().getName());
                    return integer;
                })
                .observeOn(Schedulers.io())
                .map(integer -> {
                    Log.i(TAG, "map-3:"+Thread.currentThread().getName());
                    return integer;
                })
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.i(TAG, "subscribe:"+Thread.currentThread().getName()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
