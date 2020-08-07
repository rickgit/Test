package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

//    函数节流（throttle）与函数去抖（debounce）
//防抖的核心思想是高频操作执行结束，n秒后仅执行一次；而节流是每隔一段时间就会执行一次。
public class _10_Rxjava_filter {

    @Test
    public void testDebounce(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    Thread.sleep(200);
                }
                emitter.onComplete();
            }
        }).debounce(400, TimeUnit.MILLISECONDS)
                .subscribeWith(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        System.out.println("onNext"+o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testthrottleFirst(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    Thread.sleep(200);
                }
                emitter.onComplete();
            }
        }).throttleFirst(1, TimeUnit.SECONDS)
        .subscribeWith(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("onNext"+o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
