package edu.ptu.androidtest._30_storage._50_network;

import org.junit.Test;

import java.io.IOException;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import rx.functions.Action1;

public class RetrofitTest {
    interface ServiceApi {
        @GET("/")
        Call<ResponseBody> getResponse();
        @GET("/")
        Observable<ResponseBody> getResponseObservable(@Query("index") int index);
    }
    @Test
    public void testRetrofitObservable() {
        Retrofit build = new Retrofit.Builder().baseUrl("https://www.baidu.com").
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        Observable<ResponseBody> response = build.create(ServiceApi.class).getResponseObservable(1);
        try {
            response.subscribe(new Action1<ResponseBody>() {
                @Override
                public void call(ResponseBody responseBody) {
                    System.out.println();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
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
