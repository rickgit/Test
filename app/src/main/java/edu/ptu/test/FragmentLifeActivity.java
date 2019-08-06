package edu.ptu.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import edu.ptu.androidutils.PhoneInfo;
import edu.ptu.androidutils.media.GifDecoder;
import edu.ptu.utils.utils.ClockUtils;

public class FragmentLifeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_life);
        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                getSupportFragmentManager().beginTransaction().add(R.id.fl_container,new LifeFragment()).commit();
//                startActivity(new Intent(view.getContext(),LifeActivity.class));
//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            GifDecoder gifDecoder = new GifDecoder();
//                            ClockUtils.getInstance().printDiffTime();
//                            InputStream open = view.getContext().getAssets().open("code-03.gif");
//                            gifDecoder.read(open, open.available());
//                            ClockUtils.getInstance().printDiffTime();
//                            gifDecoder.getNextFrame();
//                            ClockUtils.getInstance().printDiffTime();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ah.await(20, TimeUnit.SECONDS);
                            System.out.println("error 到时");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        ViewPager vp = (ViewPager)findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new LifeFragment();
            }

            @Override
            public int getCount() {
                return 12;
            }
        });
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getCpuInfo())+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getPhoneOsInfo(this))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getTotalMemory(this)* 1.0/ (1024 * 1024))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getAppMemory(this))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getNumberOfCPUCores())+"\n\n");
        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getCPUMaxFreqKHz()/1024./1024.)+"GHZ\n\n");
    }
    Handler h;
    CountDownLatch ah=new CountDownLatch(1);
    {
    HandlerThread ht=  new HandlerThread("HandlerThread");
    ht.start();
      h=new Handler(ht.getLooper());
  }
}
