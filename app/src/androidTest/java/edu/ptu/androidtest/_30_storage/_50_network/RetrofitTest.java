package edu.ptu.androidtest._30_storage._50_network;

import android.os.Looper;

import org.junit.Test;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class RetrofitTest {
    interface ServiceApi {
        @GET("/")
        Call<ResponseBody> getResponse();

        @GET("/")
        @Headers({"Content-Type: application/json","Accept: application/json"})
        Observable<ResponseBody> getResponseObservable(@Query("index") int index);
    }

    @Test
    public void testRetrofitObservable() {

        Retrofit build = new Retrofit.Builder().baseUrl("https://www.baidu.com").
                addConverterFactory(GsonConverterFactory.create()).
        //需要用RxJava3CallAdapterFactory
                addCallAdapterFactory(
//                        RxJava3CallAdapterFactory.createWithScheduler(AndroidSchedulers.mainThread())
                        RxJava3CallAdapterFactory.create()
//                        RxJava3CallAdapterFactory.create()
                )
                .build();//AndroidSchedulers.mainThread()
        Observable<ResponseBody> response = build.create(ServiceApi.class).getResponseObservable(1);
        try {

            //v1 没有onSubscribe，retrofit只兼容到v2；v3优化和兼容v2
            //v1 包名 rx.；v2包名 io.reactivex.；v3包名 io.reactivex.rxjava3.core.
             Observer<ResponseBody> observerV3 = new  Observer<ResponseBody>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    System.out.println("onSubscribe" + " " + (Looper.getMainLooper() == Looper.myLooper()));
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError" + " " + (Looper.getMainLooper() == Looper.myLooper()));
                }

                @Override
                public void onComplete() {
                    System.out.println("onCompleted" + " " + (Looper.getMainLooper() == Looper.myLooper()));
                    System.out.println("============="  );
                }


                @Override
                public void onNext(ResponseBody responseBody) {
                    System.out.println("onNext" + " " + (Looper.getMainLooper() == Looper.myLooper()));
                }
            };

            response.map(new Function<ResponseBody, ResponseBody>() {
                @Override
                public ResponseBody apply(ResponseBody responseBody) throws Throwable {
                    System.out.println("map" + " " + (Looper.getMainLooper() == Looper.myLooper()));
                    return responseBody;
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observerV3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRetrofit() {
        Retrofit build = new Retrofit.Builder().baseUrl("https://www.baidu.com").build();
        Call<ResponseBody> response = build.create(ServiceApi.class).getResponse();
        try {
            response.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    @Test
    public void testRetrofit2() {
        Retrofit build = new Retrofit.Builder().baseUrl("https://www.baidu.com").build();
        Call<ResponseBody> response = build.create(ServiceApi.class).getResponse();
        try {
            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
