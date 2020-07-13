package edu.ptu.javatest._80_storage._70_cache;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerformanceTest {
    public static final int time = 1_000_000_000;
    private String uri_uc_img = "http://image.uczzd.cn/5864809814117103944.jpg?id=0&from=export";
    private String uri_xiaomi_img = "http://images.cdn.pt.xiaomi.com/thumcrop/webp/w329h219q80/Feeds/07e613497c71b4a1f2a6e82f67f3f84c6bd382c76?crop=l0r816t8b552";
    ;


    /**
     * <pre>
     *      0: iconst_0
     *      1: istore_1
     *      2: iload_1
     *      3: ldc           #3                  // int 1000000000
     *      5: if_icmpge     14
     *      8: iinc          1, 1
     *      11: goto          2
     *      14: return
     * </pre>
     */
    @Test
    public void testInstructions() {
        for (int i = 0; i < time; i++) {
        }
        //环境：CPU：1.6GHz，4核心，64位
        //(5*1_000_000_000+3)个指令
        //运行：4ms
        //平均： 1250指令/ns
    }

    @Test
    public void testConnectUC() {
        for (int i = 0; i < 100; i++) {
            try {
                //uc

                URL url = new URL(uri_uc_img);
                //xiao mi
//            URL url = new URL("http://images.cdn.pt.xiaomi.com/thumcrop/webp/w329h219q80/Feeds/07e613497c71b4a1f2a6e82f67f3f84c6bd382c76?crop=l0r816t8b552");
                URLConnection urlConnection = url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testConnectMI() {
        for (int i = 0; i < 100; i++) {
            try {
                //uc

                URL url = new URL(uri_xiaomi_img);
                URLConnection urlConnection = url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testConnectJSON() {// 2核4线程,IO密集型操作：串行连接20个连接， 2s  ;2个线程，20个连接，1s656ms;4个线程，20个连接，1s199ms
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        ExecutorService executorService = Executors.newFixedThreadPool(2*2+1);
        for (int i = 0; i < 20; i++) {

            //uc
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {

                        URL url = new URL("https://www.apiopen.top/weatherApi");
                        URLConnection urlConnection = url.openConnection();

                        InputStream inputStream = urlConnection.getInputStream();

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                        }
                        countDownLatch.countDown();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadWebpFile() {
        for (int i = 0; i < 100; i++) {
            try {
                //uc
                //xiao mi
                File url = new File("./doc/07e613497c71b4a1f2a6e82f67f3f84c6bd382c76.webp");
                InputStream inputStream = new FileInputStream(url);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testReadJpgFile() {
        for (int i = 0; i < 100; i++) {
            try {
                //xiao mi
                File url = new File("./doc/5864809814117103944.jpg");
                InputStream inputStream = new FileInputStream(url);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testThreadCreate() {
        final long l = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() - l);
            }
        });
        thread.start();
    }
}
