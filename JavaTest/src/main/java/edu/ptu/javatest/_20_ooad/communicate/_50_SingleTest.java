package edu.ptu.javatest._20_ooad.communicate;

import com.google.gson.internal.bind.util.ISO8601Utils;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class _50_SingleTest {
    @Test
    public void testSingleJust() {
//        testJust();
//        testDefer();//延迟创建Observable
//        testEmpty();//直接执行complete
//        testNever();//指挥接受onSubscribe事件
//        testFromCall();//
//        testInterval();//增加线程调度功能，延迟向下游观察者onNext发送数据
//        testRangle();//增加 ObservableRange.RangeDisposable装饰，可以for循环，向下游观察者onNext发送数据
//        testTimer();// 延时，向下游观察者onNext发送数据

    }

    @Test
    public void testFilterFunc() {
//         testDebounce();//onSubscribe后，装饰DebounceTimedObserver，增加timeout，不断取消之前的DebounceObserver内的消息，重新调度。 文本输入框搜索功能
//         emitterObject().distinct().subscribe(getObserver());//装饰DistinctObserver，增加set集合，判断加入成功，则往下游发送数据
//         emitterObject().elementAt(23).subscribe(getMaybeObserver());//装饰，index和count 判断是否相等，往下游发送
//         testFilter();
//         testFirst();//装饰 ElementAtObserver，设置index=0,和默认值。
//         testLast();//装饰 ElementAtObserver，设置index=0,和默认值。
//         testLast();//装饰 LastObserver，添加item，onNext设置值，oncomplete通知下游
        //IgnoreObservable ,onNext没有触发下游
//         emitterObject().ignoreElements().subscribe(getCompletableObserver());

        // ObservableSampleTimed.SampleTimedObserver，增加AtomicReference，定时获取AtomicReference的值
//         emitterObject().sample(2,TimeUnit.SECONDS).subscribe(getObserver());
        //SkipObserver增加remaining，自减到0，才向下游发送数据
//         emitterObject().skip(2).subscribe(getObserver());
        //添加了一个上游 ObservableTimer，委托设置下游notSkipping可用；
        // ObservableSkipUntil.SkipUntilObserver 为实际观察者，添加notSkipping，判断是否分发
//         emitterObject().skip(2,TimeUnit.SECONDS).subscribe(getObserver());
        //和skip(2,TimeUnit.SECONDS)，一样。状态委托给自定义了一个上游修改，只有状态修改成功后，才会触发
//        emitterObject().skipUntil(Observable.just("1")).subscribe(getObserver());
        //SkipWhileObserver#notSkipping，直到返回false，才向下游触发数据
//         emitterObject().skipWhile(new Predicate<Object>() {
//             @Override
//             public boolean test(Object o) throws Throwable {
//                 return !o.toString().equals("+3");
//             }
//         }).subscribe(getObserver());
        //修饰类TakeObserver ，新增remaining
//         emitterObject().take(1).subscribe(getObserver());
        //修饰TakeLastObserver，继承ArrayDeque
//         emitterObject().takeLast(1).subscribe(getObserver());
        System.out.println();
        // TakeUntilMainObserver状态委托给自定义了一个上游OtherObserver修改，只有状态修改成功后，停止向下游分发
//        emitterObject().takeUntil(Observable.timer(3, TimeUnit.SECONDS)).subscribe(getObserver());
        // TakeUntilMainObserver状态委托给function修改，只有状态修改成功后，停止向下游分发
        emitterObject().takeWhile(o -> {
            boolean isUsing = new Random().nextInt(2) % 2 == 0;
            return isUsing;
        }).subscribe(getObserver());
    }

    @Test
    public void testCondition() {
        //装饰Observable#onNext， Predicate#test()
        //观察success ，判断所有元素的条件&&结果
//         testAll();
        //AnyObserver 新增done，在onComplete
//         testContains();
        //ObservableSwitchIfEmpty ,新增empty，没触发onNext时
//        Observable.create(emitter ->
//                        emitter.onComplete()
//                ).defaultIfEmpty(" Empty").subscribe(getObserver());
        // ObservableSwitchIfEmpty检查本地缓存逻辑判断
//        Observable.create(emitter ->
//                emitter.onComplete()
//        ).switchIfEmpty(new ObservableSource<Object>() {
//            @Override
//            public void subscribe(@NonNull Observer<? super Object> observer) {
//                observer.onNext("Empty");
//                System.out.printf(" Empty");
//                observer.onComplete();
//            }
//        }).subscribe(getObserver());

    }

    @Test
    public void testTransform() {
        //转化为容器ArrayList
        // 新增ObservableBuffer.BufferExactObserver.count buffer容量， ObservableBuffer.BufferExactObserver.size计数当前buffer，达到容量，将容器下游分发
//        emitterObject().buffer(3).subscribe(getObserver());
        //转化为数据源
//        testMap();
//        //转化为Observable数据源
        testFlatMap();

//      转化  GroupedObservable
//        testGroupBy();
        //与前一个数据进行转化，转化为另一个
//        emitterObject().scan(new BiFunction<Object, Object, Object>() {
//            @Override
//            public Object apply(Object pre, Object cur) throws Throwable {
//                return pre.toString()+cur.toString();
//            }
//        }).subscribe(getObserver());
        // 转化UnicastSubject.create(int, java.lang.Runnable)； 装饰 ObservableWindow.WindowExactObserver.window，每次发送源达到窗口，重置，重新创建
//        testWindow();
    }

    @Test
    public void testConbining() {
//        @NonNull Observable<String> left = Observable.interval(100, TimeUnit.MILLISECONDS).map(i -> "" + i);
//        @NonNull Observable<String> rith = Observable.interval(100, TimeUnit.MILLISECONDS).map(i -> "" + i);
//        left.join(rith,
//                i->Observable.never(),
//                i->Observable.timer(200,TimeUnit.MILLISECONDS),
//                (l,r)->l+"---"+r)
//                .take(13)
//                .subscribe(System.out::println);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //ObservableFromArray 依次触发 ObservableFlatMap的onNext
        //ObservableFromArray->ObservableFlatMap.MergeObserver-> ObservableFlatMap.InnerObserver
//        emitterObject().mergeWith(Observable.just("merge")).subscribe(getObserver());
        //ObservableFromArray->ObservableConcatMap.DelayErrorInnerObserver
//        emitterObject().startWith(Observable.just("+4")).subscribe(getObserver());
    }

    @Test
    public void testMath() {
        //ObservableCountSingle 新增count；
//        testCount();

        //ObservableConcatMap 新增ObservableFromArray，
//        Observable
//                .concat(Observable.just("1"),Observable.just("3"))
//                .subscribe(getObserver());

        //
//                emitterObject().reduce(new BiFunction<Object, Object, Object>() {
//            @Override
//            public Object apply(Object o, Object o2) throws Throwable {
//                return null;
//            }
//        }, new BiFunction<BiFunction<Object, Object, Object>, Object, BiFunction<Object, Object, Object>>() {
//            @Override
//            public BiFunction<Object, Object, Object> apply(BiFunction<Object, Object, Object> objectObjectObjectBiFunction, Object o) throws Throwable {
//                return null;
//            }
//        }).subscribe(new SingleObserver<BiFunction<Object, Object, Object>>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(@NonNull BiFunction<Object, Object, Object> objectObjectObjectBiFunction) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//        });
    }

    int retryCount = 3;

    @Test
    public void testError() {


//       1. onErrorReturn:让Observable遇到错误时发射一个特殊的项并且正常终止。
//        getErrorObservable().onErrorReturn(
//                throwable -> " throwable"
//        ).subscribe(getObserver());
//        getErrorObservable().onErrorResumeNext(
//                throwable ->
//                        Observable.just(" throwable")
//
//        ).subscribe(getObserver());
//        getErrorObservable().onErrorComplete(throwable -> //false走onError;trueComplete
//                false
//        ).subscribe(getObserver());

//        2. onErrorResumeNext:让Observable在遇到错误时开始发射第二个Observable的数据序列。与服务器间 token 机制
//        getErrorObservable().onErrorResumeWith(new Observable<Object>() {
//            @Override
//            protected void subscribeActual(@NonNull Observer<? super Object> observer) {
//                observer.onNext(" error");
//                observer.onComplete();
//            }
//        }).subscribe(getObserver());

// 1 .
//        Observable
//                .create(emitter -> {
//                    System.out.println("emitter");
//                    emitter.onNext(null);
//                }).retry().subscribe(getObserver());//错误则无限重试
        //2. retry+interval 重试多次
//        testRetryWhen();
        //3. retry+range 有限次数的重订阅
//        testRetryWhenZip();
        //4. 有限次数延迟加长重订阅
//        testRetryWhenZipTimer();

//        emitterObject().repeat().subscribe(getObserver());//重发一次
//        Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                System.out.println("subscribe");
//                emitter.onNext("001");
//            }
//        }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Throwable {
//                System.out.println("repeatWhen");
//                return objectObservable.delay(1, TimeUnit.SECONDS);
//            }
//        }).subscribe(getObserver());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private Observable<Object> getErrorObservable() {
        return Observable.create(emitter -> {
            for (int i = 0; i < 6; i++) {
                if (i == 3)
                    emitter.onNext(null);
                else emitter.onNext(i);
            }
            emitter.onComplete();
        });
    }

    private void testRetryWhenZipTimer() {
        Observable
                .create(emitter -> {
                    System.out.println("emitter");
                    Thread.sleep(1000);
                    emitter.onNext(null);
                })
                .retryWhen(throwableObservable -> {
                    return throwableObservable.zipWith(
                            Observable.range(1, 3),
                            (BiFunction<Throwable, Integer, Object>) (throwable, integer) ->
                                    integer
                    ).flatMap((Function<Object, ObservableSource<?>>) o ->
                            Observable.timer((long) Math.pow(5, retryCount), TimeUnit.MILLISECONDS)
                    );
                })
                .subscribe(getObserver());
    }

    private void testRetryWhenZip() {
        Observable
                .create(emitter -> {
                    System.out.println("emitter");
                    Thread.sleep(1000);
                    emitter.onNext(null);
                })
                .retryWhen(throwableObservable -> {
                    return throwableObservable.zipWith(
                            Observable.range(1, 3),
                            (BiFunction<Throwable, Integer, Object>) (throwable, integer) ->
                                    integer
                    );
                })
                .subscribe(getObserver());
    }

    private void testRetryWhen() {
        Observable
                .create(emitter -> {
                    System.out.println("emitter");
                    Thread.sleep(1000);
                    emitter.onNext(null);
                })
                .retryWhen(throwableObservable -> {
                    return throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                        System.out.println("throwable");
                        System.out.println("");
                        if (retryCount > 0) {
                            retryCount--;
//                            return Observable.timer(1000, TimeUnit.MILLISECONDS);
                            return Observable.interval(1000, TimeUnit.MILLISECONDS);//推荐interval 代替timer
                        }
                        return Observable.error((Throwable) throwable);
                    });
                })
                .subscribe(getObserver());
    }

    @Test
    public void testUtil() {
//        emitterObject().serialize().subscribe(getObserver());
//        emitterObject().materialize().subscribe(getObserver());
//         emitterObject().dematerialize( ).subscribe(getObserver());//未调试成功
        //使用AtomicReference，调试
//        testDispose();

        //事件调度
        //DelayObserver，新增调度，onNext延迟处理
//        emitterObject().delay(3,TimeUnit.SECONDS).subscribe(getObserver());
        //TimeoutObserver 新增调度，onNext调度一个延时错误，超时执行onError，取消分发
//        emitterObject().timeout(1,TimeUnit.SECONDS).subscribe(getObserver());
        //TimeIntervalObserver ，新增lastTime，计算与上一个数据的时间差
//        emitterObject().timeInterval().subscribe(getObserver());
        //ObservableMap，增加TimestampFunction，调用是转化为时间戳装饰类Timed
//        emitterObject().timestamp().subscribe(getObserver());

//        testDoOnDispose();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void testDoOnDispose() {
        @NonNull Disposable doOnDispose = emitterObject().doOnDispose(new Action() {
            @Override
            public void run() throws Throwable {
                System.out.println("doOnDispose");
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("accept " + o);
            }
        });
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!doOnDispose.isDisposed())
            doOnDispose.dispose();
    }

    private void testDispose() {
        @NonNull Disposable subscribe = emitterObject()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        System.out.println("Object" + o);
                    }
                });
        if (!subscribe.isDisposed())
            subscribe.dispose();
    }

    private void testCount() {
        emitterObject().count().subscribe(new SingleObserver<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Long aLong) {
                System.out.println("onSuccess" + aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }
        });
    }

    private void testWindow() {
        emitterObject().window(3).subscribe(new Observer<Observable<Object>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Observable<Object> objectObservable) {
                objectObservable.subscribe(getObserver());
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

    private void testGroupBy() {
        emitterObject().groupBy(new Function<Object, Object>() {
            int i;

            @Override
            public Object apply(Object o) throws Throwable {
                return (i++) % 3;//转化key
            }
        }).subscribe(new Consumer<GroupedObservable<Object, Object>>() {
            @Override
            public void accept(GroupedObservable<Object, Object> objectObjectGroupedObservable) throws Throwable {
                if (objectObjectGroupedObservable.getKey().toString().equals("0"))
                    objectObjectGroupedObservable.subscribe(getObserver());
            }
        });
    }

    private void testFlatMap() {
        emitterObject().flatMap(new Function<Object, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Object o) throws Throwable {
                return Observable.fromArray(o.toString().toCharArray());
            }
        }).subscribe(getObserver());
    }

    private void testMap() {
        emitterObject().map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Throwable {
                return o;
            }
        }).subscribe(getObserver());
    }

    private void testContains() {
        emitterObject().contains("+" + 1).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                System.out.println("onSuccess" + aBoolean);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }
        });
    }

    private void testAll() {
        emitterObject().all(new Predicate<Object>() {
            int i;

            @Override
            public boolean test(Object o) throws Throwable {
                return (i = i + 1) % 1 != 0;
            }
        }).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                System.out.println("onSuccess" + aBoolean);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }
        });
    }

    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

        };
    }

    private void testLast() {
        emitterObject().last(new Object()).subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Object o) {
                System.out.println("onSuccess" + o);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }


        });
    }

    private void testFirst() {
        emitterObject().first(new Object()).subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Object o) {
                System.out.println("onSuccess" + o);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }


        });
    }

    private void testFilter() {
        emitterObject().filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Throwable {
                return false;
            }
        }).subscribe(getObserver());
    }

    private MaybeObserver<Object> getMaybeObserver() {
        return new MaybeObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onSuccess(@NonNull Object o) {
                System.out.println("onSuccess" + o);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }

    private void testDebounce() {
        emitterObject().debounce(1, TimeUnit.SECONDS).subscribe(getObserver());
    }

    private Observer<Object> getObserver() {
        return new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("o" + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }

    @NonNull
    private Observable<Object> emitterObject() {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(200 * i);
                    emitter.onNext("+" + i);
                }
                emitter.onComplete();
            }
        });
    }

    private void testTimer() {
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testRangle() {
        Observable.range(1, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testInterval() {
        Observable.interval(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testFromCall() {
        Single.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "null";
            }
        }).subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    private void testNever() {
        Single.never().subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println();
            }

            @Override
            public void onSuccess(@NonNull Object o) {
                System.out.println();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println();
            }
        });
    }

    private void testEmpty() {
        Observable.empty().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println();
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println();
            }

            @Override
            public void onComplete() {
                System.out.println();
            }
        });
    }

    private void testJust() {
        Single.just("1")//SingleJust
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("");
                    }

                    @Override
                    public void onSuccess(@NonNull String s) {
                        System.out.println("");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("");
                    }
                });
    }

    private void testDefer() {
        Single.defer(new Supplier<SingleSource<?>>() {
            @Override
            public SingleSource<?> get() throws Throwable {
                return Single.just("1");//延迟创建数据源
            }
        }).subscribe(new SingleObserver<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
