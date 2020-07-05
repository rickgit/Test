package edu.ptu.androidtest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.concurrent.CountDownLatch;

import edu.ptu.androidutils.PhoneInfo;
import edu.ptu.androidtest.test.ImageTest;
import edu.ptu.utils.utils.ClockUtils;

public class FragmentLifeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClockUtils.getInstance().printDiffTime(this);
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
//                h.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            ah.await(20, TimeUnit.SECONDS);
//                            System.out.println("error 到时");
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                EventBrocastTest.sendLocalBrocast(view.getContext());
//                EventBrocastTest.postEventBus();
//                NetworkTest.testVolley(view.getContext());
                ImageTest.testLoadImage(view.getContext(), (ViewGroup)view.getParent());
            }
        });
//        EventBrocastTest.registLocalBrocast(this);
//        EventBrocastTest.registEventBus();
//        EventBrocastTest.registBrocast(this);
        ViewPager vp = (ViewPager)findViewById(R.id.vp);
        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        System.out.println("getIntrinsicWidth: "+drawable.getIntrinsicWidth());
    }
    Handler h;
    CountDownLatch ah=new CountDownLatch(1);
    {
    HandlerThread ht=  new HandlerThread("HandlerThread");
    ht.start();
      h=new Handler(ht.getLooper());
  }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ClockUtils.getInstance().printDiffTime(this);

    }


}
