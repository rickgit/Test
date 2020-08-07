package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;


public class _40_Rxjava_aggregate {

    @Test
    public void testConcat() {
        //保证连接后有序；请求多个接口后，数据拼接，触发界面刷新
        Observable.concat(Observable.just("用户头像"), Observable.just("用户信息", "用户硬币"))
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
    public void testSwitchIfNull() {
        //用户上传头像
//        Observable.empty()//"本地数据null
        Observable.just("请求数据").flatMap(new Function<Object, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Object o) throws Throwable {
                //没有获取到本地数据
                return Observable.empty();
            }
        })
                .switchIfEmpty(Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        emitter.onNext("加载服务器资源");
                        emitter.onComplete();
                    }
                }))

                .subscribeWith(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("subscribeWith onSubscribe" + d.toString());
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
    public void testZip() {
        //用户上传头像
        Observable.zip(Observable.just("获取图像local"), Observable.just("上传图片url"), new BiFunction<String, String, Object>() {
            @Override
            public Object apply(String s, String s2) throws Throwable {
                System.out.println(s + " :" + s2);

                return "[" + s + " " + s2 + "]";//这里应该还需要
            }
        })//上传图片，更新用户信息
                .subscribeWith(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("subscribeWith onSubscribe" + d.toString());
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
    public void testConbin(){
        Observable.combineLatest(Observable.just("个人信息"), Observable.just("其他用户"), new BiFunction<String, String, Object>() {
            @Override
            public Object apply(String s, String s2) throws Throwable {
                return "["+s+", "+" "+s2+"]";
            }
        }) .subscribeWith(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("subscribeWith onSubscribe" + d.toString());
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
