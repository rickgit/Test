package edu.ptu.androidtest.android.act;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import edu.ptu.androidtest.R;
import edu.ptu.androidtest.android.broadcast.StandardBroadCastReceiver;

public class LauchmodeUIActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lauchmode);
        ((TextView) findViewById(R.id.thisclassname)).setText(this.getClass().getName());


        startActivity(R.id.standard, LauchmodeStandardUIActivity.class);
        startActivity(R.id.singleTop, LauchmodeSingleTopUIActivity.class);
        startActivity(R.id.singleTask, LauchmodeSingleTaskUIActivity.class);
        startActivity(R.id.singleinstance, LauchmodeSingleInstanceUIActivity.class);

//        final int flags = Intent.FLAG_ACTIVITY_SINGLE_TOP;
//        final int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
//        final int flags = Intent.FLAG_ACTIVITY_NO_HISTORY;

        final int flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;

        startActivity(R.id.standardflag, LauchmodeStandardUIActivity.class, flags);
        startActivity(R.id.singleTopFlag, LauchmodeSingleTopUIActivity.class, flags);
        startActivity(R.id.singleTaskFlag, LauchmodeSingleTaskUIActivity.class, flags);
        startActivity(R.id.singleInstanceFlag, LauchmodeSingleInstanceUIActivity.class, flags);

        startActivity(R.id.standard2, LauchmodeStandardUI2Activity.class);
        startActivity(R.id.singleTop2, LauchmodeSingleTopUI2Activity.class);
        startActivity(R.id.singleTask2, LauchmodeSingleTaskUI2Activity.class);
        startActivity(R.id.singleinstance2, LauchmodeSingleInstanceUI2Activity.class);

        startActivity(R.id.standardDialog, LauchmodeStandardUIDialogActivity.class);
        startActivity(R.id.singleTopProcess, LauchmodeSingleTopUIDialogActivity.class);
        startActivity(R.id.singleTaskDialog, LauchmodeSingleTaskUIDialogActivity.class);
        startActivity(R.id.singleinstanceDialog, LauchmodeSingleInstanceUIDialogActivity.class);

        startActivity(R.id.standardProcess, LauchmodeStandardUIProcessActivity.class);
        startActivity(R.id.singleTopProcess, LauchmodeSingleTopUIProcessActivity.class);
        startActivity(R.id.singleTaskProcess, LauchmodeSingleTaskUIProcessActivity.class);
        startActivity(R.id.singleinstanceProcess, LauchmodeSingleInstanceUIProcessActivity.class);

        registerReceiver(new StandardBroadCastReceiver(), new IntentFilter("registerReceiver"));
        LocalBroadcastManager.getInstance(this).registerReceiver(new StandardBroadCastReceiver(), new IntentFilter(LocalBroadcastManager.class.getSimpleName()));
//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("registerReceiver"));
//        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LocalBroadcastManager.class.getSimpleName()));
//        sendBroadcast(new Intent("registerReceiver"));
//        sendBroadcast(new Intent(LocalBroadcastManager.class.getSimpleName()));
    }

    public void startActivity(@IdRes int id, Class actclass, int flags) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), actclass);
                intent.setFlags(flags);
                startActivity(intent);
            }
        });
    }

    public void startActivity(@IdRes int id, Class actclass) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), actclass);
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
