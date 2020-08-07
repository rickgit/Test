package edu.ptu.javatest._20_ooad.communicate;

import org.junit.Test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;


public class _50_error {
    @Test
    public void testErrorResumeNext() {
//token
        Observable.error(new Throwable("token过期")).onErrorResumeNext(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                return Observable.just("token 出问题，发送token请求");
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
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    public void testRetryWhen() {
//token
         final int time = 0,maxtime=3;
        Observable.error(new Throwable("token过期")).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Throwable {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                       int tmp= time+1;
                        if ( tmp>maxtime){
                            return Observable.error(new Throwable("重试失败"));
                        }
                        return Observable.just("...");
                    }
                });
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
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
