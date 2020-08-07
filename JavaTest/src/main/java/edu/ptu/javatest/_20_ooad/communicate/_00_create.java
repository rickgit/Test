package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableMaybeObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

//io.reactivex.rxjava3.core.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
//io.reactivex.rxjava3.core.Observable: 0..N flows, no backpressure,
//io.reactivex.rxjava3.core.Single: a flow of exactly 1 item or an error,
//io.reactivex.rxjava3.core.Completable: a flow without items but only a completion or error signal,
//io.reactivex.rxjava3.core.Maybe: a flow with no items, exactly one item or an error.
public class _00_create {
    @Test
    public void testTestFilter() {

        Observable.just("Hello world").subscribe(System.out::println);
        Single.just("Hello World").subscribe(System.out::println);
        Disposable d = Maybe.just("Hello World")
                .delay(10, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableMaybeObserver<String>() {
                    @Override
                    public void onStart() {
                        System.out.println("Started");
                    }

                    @Override
                    public void onSuccess(String value) {
                        System.out.println("Success: " + value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        Completable.complete().delay(10, TimeUnit.SECONDS, Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onStart() {
                        System.out.println("Started");
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        //需要后背情况
        Flowable.just("Hello world").take(1).subscribe(System.out::println);
        try {
            Thread.sleep(13333);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestObserveOn() {

        Observable.just("Hello world")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Throwable {
                        System.out.println("onSubscribe " + Thread.currentThread());
                        return "map 1";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("onNext " + Thread.currentThread());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError " + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete " + Thread.currentThread());
                    }
                });
        try {
            Thread.sleep(3333);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestSubscribeOn() {

        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                System.out.println("subscribe " + Thread.currentThread());
            }
        })
        .subscribeOn(Schedulers.newThread())
        .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                System.out.println("accept1 " + Thread.currentThread());
            }
        })
        .subscribeOn(Schedulers.computation())
        .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                System.out.println("accept2 " + Thread.currentThread());
            }
        })
        .subscribe(s -> {//onnext
            System.out.println("onComplete " + Thread.currentThread());
        });
        try {
            Thread.sleep(3333);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDefer(){
//        defer
//        直到有订阅者才创建observable，并且为每个订阅者创建一个全新的observable。
        Observable.defer(new Supplier<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> get() throws Throwable {
                return new Observable<Object>() {
                    @Override
                    protected void subscribeActual(@NonNull Observer<? super Object> observer) {

                    }
                };
            }
        }).subscribe(s -> {//onnext
            System.out.println("onComplete " + Thread.currentThread());
        });
        try {
            Thread.sleep(3333);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInter(){//短信倒计时
        int INTERVAL_TIME=3000;
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS) // ，每秒发射一次事件
                //如果aLong小于等于我们设定的秒数，发射事件
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Throwable {
                        return aLong <INTERVAL_TIME;
                    }
                })

        //可以通过filter达到takeWhile相同的效果
//                .filter(new Func1<Long, Boolean>() {
//                    @Override
//                    public Boolean call(Long aLong) {
//                        return aLong <= INTERVAL_TIME;
//                    }
//                })
                .subscribe(s -> {//onnext
            System.out.println("onComplete " + Thread.currentThread());
        });
        try {
            Thread.sleep(3333);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
