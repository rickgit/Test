package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class _01_backpress {

    //在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略
    //BUFFER就是把RxJava中默认的只能存128个事件的缓存池换成一个大的缓存池，支持存很多很多的数据。
    @Test
    public void testBpBUFFER() {
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 128*5; j++) {//128*3
                e.onNext(j);
                System.out.println(" 发送数据：" + j);
//                try {
//                    Thread.sleep(50);
//                } catch (Exception ex) {
//                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //观察者设置接收事件的数量,如果不设置接收不到事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError : " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBpERROR() {
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 150; j++) {
                e.onNext(j);
                System.out.println(" 发送数据：" + j);
//                try {
//                    Thread.sleep(50);
//                } catch (Exception ex) {
//                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //观察者设置接收事件的数量,如果不设置接收不到事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(Throwable t) {//io.reactivex.rxjava3.exceptions.MissingBackpressureException: create: could not emit value due to lack of requests
                        System.out.println("onError : " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    每当观察者接收128事件之后，就会丢弃部分事件。
    @Test
    public void testBpDROP() {
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 150; j++) {
                e.onNext(j);
                System.out.println(" 发送数据：" + j);
//                try {
//                    Thread.sleep(50);
//                } catch (Exception ex) {
//                }
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //观察者设置接收事件的数量,如果不设置接收不到事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError : " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    LATEST与DROP使用效果一样，但LATEST会保证能接收最后一个事件，而DROP则不会保证。
    @Test
    public void testBpLATEST() {//Drop oldest
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 150; j++) {
                e.onNext(j);
//                System.out.println(" 发送数据：" + j);
                try {
                    Thread.sleep(50);
                } catch (Exception ex) {
                }
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //观察者设置接收事件的数量,如果不设置接收不到事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError : " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //和Observable 缓冲区的大小为 128 个 items

    @Test
    public void testBpMISSING() {
        Flowable.create((FlowableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 260; j++) {//  io.reactivex.rxjava3.exceptions.MissingBackpressureException: Queue is full?!
                e.onNext(j);
                System.out.println(" 发送数据：" + j);
                try {
                    Thread.sleep(50);
                } catch (Exception ex) {
                }
            }
        }, BackpressureStrategy.MISSING)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE); //观察者设置接收事件的数量,如果不设置接收不到事件
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println("onError : " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBpObservable() {//默认有缓存了
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            for (int j = 0; j <= 1260; j++) {//
                e.onNext(j);
                System.out.println(" 发送数据：" + j);
//                try {
//                    Thread.sleep(50);
//                } catch (Exception ex) {
//                }
            }
        } )
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext : " + (integer));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError : " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        try {
            Thread.sleep(130000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
