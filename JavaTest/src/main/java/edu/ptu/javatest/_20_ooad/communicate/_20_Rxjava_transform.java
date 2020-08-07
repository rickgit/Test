package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class _20_Rxjava_transform {
    @Test
    public void testMap() {
        Observable.fromArray(new Object[]{1, 23, 4324}).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Throwable {
                return "null" + o;
            }
        }).subscribeWith(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("onNext");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    //将上游的事件，转化为单独分发的事件
    //获取上游事件的具体信息，再分发出去
    @Test
    public void testFMap() {
        Observable.fromArray(new Object[]{new Integer[]{132, 134, 1234},
                new Integer[]{211, 222, 223},
                new Integer[]{311, 322, 323}}).flatMap(new Function<Object, ObservableSource<?>>() {
            @Override
            public ObservableSource<Integer> apply(Object o) throws Throwable {
                //不一定是连续
                return Observable.fromArray((Integer[]) o).delay(200, TimeUnit.MILLISECONDS);
            }
        })
                .subscribeWith(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("subscribeWith onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        System.out.println("subscribeWith onNext" + o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCMap() {
        Observable.fromArray(new Object[]{new Integer[]{132, 134, 1234}, new Integer[]{211, 222, 223}, new Integer[]{311, 322, 323}}).concatMap(new Function<Object, ObservableSource<?>>() {
            @Override
            public ObservableSource<Integer> apply(Object o) throws Throwable {
                //异步后是连续
                return Observable.fromArray((Integer[]) o).delay(200, TimeUnit.MILLISECONDS);
            }
        })
                .subscribeWith(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("subscribeWith onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        System.out.println("subscribeWith onNext" + o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
