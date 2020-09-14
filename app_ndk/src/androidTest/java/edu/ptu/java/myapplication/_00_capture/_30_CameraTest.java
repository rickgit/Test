package edu.ptu.java.myapplication._00_capture;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

public class _30_CameraTest {

    private CameraDevice.StateCallback callback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {

        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {

        }
    };

    @Test
    public void testCamera2(){
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final CameraManager manager=(CameraManager)targetContext.getSystemService(Context.CAMERA_SERVICE);
        manager.registerAvailabilityCallback(new CameraManager.AvailabilityCallback(){
            @Override
            public void onCameraAvailable(String cameraId)    {
                super.onCameraAvailable(cameraId);

                System.out.println("cameraId");//CameraCharacteristics.LENS_FACING_FRONT
                if (cameraId.equals(String.valueOf(CameraCharacteristics.LENS_FACING_FRONT))){
                    try {
                        manager.openCamera(cameraId, callback,new Handler(Looper.getMainLooper()){
                            @Override
                            public void handleMessage(@NonNull Message msg) {
                                super.handleMessage(msg);
                            }
                        });
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCameraUnavailable(String cameraId)    {
                super.onCameraUnavailable(cameraId);
            }
        },new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
