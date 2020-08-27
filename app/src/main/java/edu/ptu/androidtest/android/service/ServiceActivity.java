package edu.ptu.androidtest.android.service;

import android.app.Service;
import android.app.job.JobService;
import android.app.job.JobServiceEngine;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import edu.ptu.androidtest.R;
import edu.ptu.androidtest.android.act.LauchmodeSingleInstanceUI2Activity;
import edu.ptu.androidtest.android.act.LauchmodeSingleInstanceUIDialogActivity;
import edu.ptu.androidtest.android.act.LauchmodeSingleTaskUI2Activity;
import edu.ptu.androidtest.android.act.LauchmodeSingleTaskUIDialogActivity;
import edu.ptu.androidtest.android.act.LauchmodeSingleTopUI2Activity;
import edu.ptu.androidtest.android.act.LauchmodeSingleTopUIDialogActivity;
import edu.ptu.androidtest.android.act.LauchmodeStandardUI2Activity;
import edu.ptu.androidtest.android.act.LauchmodeStandardUIDialogActivity;

public class ServiceActivity extends FragmentActivity {
    private ServiceConnection sc;
    private ServiceConnection scPc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_service);
        ((TextView) findViewById(R.id.thisclassname)).setText(this.getClass().getName());

        findViewById(R.id.startservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(v.getContext(), StandardService.class));
            }
        });
        findViewById(R.id.stopservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), StandardService.class));
            }
        });
        findViewById(R.id.bindservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(v.getContext(), StandardService.class), ServiceActivity.this.sc = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Service.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unbindservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sc != null) {
                    unbindService(sc);
                    sc = null;
                }
            }
        });

        findViewById(R.id.startProcessService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(v.getContext(), ProcessService.class));
            }
        });
        findViewById(R.id.stopProcessService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(v.getContext(), ProcessService.class));
            }
        });
        findViewById(R.id.bindProcessService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(v.getContext(), ProcessService.class), ServiceActivity.this.scPc = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Service.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unbindProcessService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scPc != null) {
                    unbindService(scPc);
                    scPc = null;
                }
            }
        });


        findViewById(R.id.standard2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.singleTop2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleTopUI2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.singleTask2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleTaskUI2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.singleinstance2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleInstanceUI2Activity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.standardDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeStandardUIDialogActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.singleTopProcess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleTopUIDialogActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.singleTaskDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleTaskUIDialogActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.singleinstanceDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LauchmodeSingleInstanceUIDialogActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
