package edu.ptu.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.io.InputStream;

import edu.ptu.test.utils.ClockUtils;
import edu.ptu.test.utils.media.GifDecoder;

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
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GifDecoder gifDecoder = new GifDecoder();
                            ClockUtils.getInstance().printDiffTime();
                            InputStream open = view.getContext().getAssets().open("code-03.gif");
                            gifDecoder.read(open, open.available());
                            ClockUtils.getInstance().printDiffTime();
                            gifDecoder.getNextFrame();
                            ClockUtils.getInstance().printDiffTime();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getCpuInfo())+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getPhoneOsInfo(this))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getTotalMemory(this)* 1.0/ (1024 * 1024))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getAppMemory(this))+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getNumberOfCPUCores())+"\n\n");
//        System.out.println("\n\n"+new Gson().toJson(PhoneInfo.getCPUMaxFreqKHz())+"\n\n");
    }
}
