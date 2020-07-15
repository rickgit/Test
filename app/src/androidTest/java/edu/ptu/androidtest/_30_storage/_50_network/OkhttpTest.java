package edu.ptu.androidtest._30_storage._50_network;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//http://www.bejson.com/knownjson/webInterface/  jsonapi提供
//http://tools.jb51.net/code/json2javabean JavaBean转化
public class OkhttpTest {
    OkHttpClient okHttpClient = new OkHttpClient();

    @Test
    public void testNetworkConnect() {
        Request request = getRequest();
//        showTime(okHttpClient.newCall(request));//44_488800 ns
//        showTime(okHttpClient.newCall(request));//5_573400 ns 使用缓存,降低10倍访问时间
        for (int i = 0; i < 10; i++) {
            showTime(okHttpClient.newCall(request));
        }

    }

    @Test
    public void testNetworkAsyncConnect() {
        Request request = getRequest();
        //154_922_500 ns 没有使用缓存，并且慢了5倍
        for (int i = 0; i < 10; i++) {
            showAsyncTime(okHttpClient.newCall(request));
        }
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJson() {
        Request request = getRequest();
        for (int i = 0; i < 10; i++) {
            //23_030_400
            // 5_430_000
            showGsonTime(okHttpClient.newCall(request));
        }
    }
    @Test
    public void testJsonAsync() {
        Request request = getRequest();
        for (int i = 0; i < 10; i++) {//相比直接访问慢了5倍
            //144_977700
            //157_140900
            showGsonAsyncTime(okHttpClient.newCall(request));
        }
    }
    @NotNull
    private Request getRequest() {
        Request.Builder build = new Request.Builder();
        build.url("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111");
        return build.build();
    }


    public static class Data {

        private String time;
        private String ftime;
        private String context;
        private String location;

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getFtime() {
            return ftime;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getContext() {
            return context;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return location;
        }

    }

    public static class JsonResponseBean {

        private String message;
        private String nu;
        private String ischeck;
        private String condition;
        private String com;
        private String status;
        private String state;
        private List<Data> data;

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getNu() {
            return nu;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getCom() {
            return com;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public List<Data> getData() {
            return data;
        }

    }

    public static void showGsonTime(Call call) {
        long l = System.nanoTime();
        try {
            Response execute = call.execute();
            new Gson().fromJson(new InputStreamReader(execute.body().byteStream()), JsonResponseBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(l = (System.nanoTime() - l));//5_362_900
    }
    public static void showGsonAsyncTime(Call call) {
        long l = System.nanoTime();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println((System.nanoTime() - l));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println((System.nanoTime() - l));//5_362_900
            }
        });

    }

    public static void showTime(Call call) {
        long l = System.nanoTime();
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(l = (System.nanoTime() - l));//5_362_900
    }

    public static void showAsyncTime(Call call) {
        long l = System.nanoTime();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println((System.nanoTime() - l));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println((System.nanoTime() - l));//5_362_900
            }
        });


    }
}
