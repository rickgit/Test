package edu.ptu.test.test;

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

import edu.ptu.utils.utils.ClockUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

//https://www.apiopen.top/api.html#c14353b903984e699c31c08f639baaff
public class NetworkTest {
    static int time = 0;

    public static void testVolley(final Context context) {
        if (time == 0)
            ClockUtils.getInstance().printDiffTime();
        Volley.newRequestQueue(context).add(new JsonObjectRequest(Request.Method.GET, "https://www.apiopen.top/weatherApi", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (time == 20) {//3729ms 4494ms
                    time = 0;
                    ClockUtils.getInstance().printDiffTime(this,"volley网络");
                    testOkhttp(context);
                } else {
                    time++;
                    testVolley(context);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ClockUtils.getInstance().printDiffTime(this, error.getMessage());
            }
        }));
    }

    static OkHttpClient client = new OkHttpClient();

    public static void testOkhttp(final Context context) {
        if (time == 0)
            ClockUtils.getInstance().printDiffTime();

        HashMap<Class<?>, ? extends Object> classStringHashMap = new HashMap<>();
        client.newCall(
                new okhttp3.Request(HttpUrl.parse("https://www.apiopen.top/weatherApi"),
                        "get", Headers.of(), null, classStringHashMap))
                .enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                ClockUtils.getInstance().printDiffTime(this, e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                if (time == 20) {//3200ms 4306ms
                    time = 0;
                    ClockUtils.getInstance().printDiffTime(this,"okhttp网络");
                } else {
                    time++;
                    testOkhttp(context);
                }
            }
        });

    }
}
