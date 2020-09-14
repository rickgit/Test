package edu.ptu.androidtest.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class DialogActivity extends FragmentActivity {

    private Button view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new Button(this);
        setContentView(view);
//        showDialog(DialogActivity.this);


    }
    private void requestOverlayPermission() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
            return;
        }

        Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        myIntent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(myIntent, 1234);
    }
    private void showDialog(Context activity) {
       requestOverlayPermission();//api level 23 需要有权限

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        Dialog dialog = new Dialog(activity);
            dialog.getWindow().setType(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setContentView(new Button(activity));
        dialog.show();
    }

    private void showDialog(Activity activity) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(new Button(activity));
        if (!activity.isFinishing() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && !activity.isDestroyed())
            dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
//                finish();
//                showPopupWindow(DialogActivity.this);
                showDialog(getApplication());
            }
        });
    }

    private void showPopupWindow(Activity activity) {
        PopupWindow popupWindow = new PopupWindow(DialogActivity.this);
        popupWindow.setContentView(new Button(DialogActivity.this));
        if (!activity.isFinishing() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && !activity.isDestroyed())
            popupWindow.showAsDropDown(view);
    }
}
