package edu.ptu.androidtest.test;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import edu.ptu.utils.utils.ClockUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

//https://www.apiopen.top/api.html#c14353b903984e699c31c08f639baaff
public class NetworkTest {
    static final int time=20;

    public static void testVolley(final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final CountDownLatch cdl = new CountDownLatch(time);
                for (int i = 0; i < time; i++) {
                    Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.GET, "https://www.apiopen.top/weatherApi", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            cdl.countDown();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            ClockUtils.getInstance().printDiffTime(this, error.getMessage());
                            cdl.countDown();
                        }
                    }));
                }
                ClockUtils.getInstance().printDiffTime();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClockUtils.getInstance().printDiffTime(this, "volley网络");//8核心，串行连接20次：   3729ms 4494ms ;3核并行，394ms
                testOkhttp(context);
            }
        }).start();

    }

    static OkHttpClient client = new OkHttpClient();

    public static void testOkhttp(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final CountDownLatch cdl = new CountDownLatch(time);

                HashMap<Class<?>, ? extends Object> classStringHashMap = new HashMap<>();
                for (int i = 0; i < time; i++) {
                    Call call = client.newCall(
                            new okhttp3.Request(HttpUrl.parse("https://www.apiopen.top/weatherApi"),
                                    "get", Headers.of(), null, classStringHashMap));
                    call
                            .enqueue(new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    ClockUtils.getInstance().printDiffTime(this, e.getMessage());
                                    cdl.countDown();
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                                    cdl.countDown();
                                }
                            });
                }
                ClockUtils.getInstance().printDiffTime();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClockUtils.getInstance().printDiffTime(this, "okhttp网络");//8核心，串行连接20个地址： 3200ms 4306ms  ;3核并行，1096ms

            }
        }).start();


    }
}
