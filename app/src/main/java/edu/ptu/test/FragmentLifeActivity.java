package edu.ptu.test;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

public class FragmentLifeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_life);
        View btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().add(R.id.fl_container,new LifeFragment()).commit();
//                startActivity(new Intent(view.getContext(),LifeActivity.class));
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
