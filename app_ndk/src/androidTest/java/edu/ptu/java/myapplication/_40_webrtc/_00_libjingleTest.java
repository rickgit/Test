package edu.ptu.java.myapplication._40_webrtc;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.CameraEnumerator;
import org.webrtc.Logging;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoCapturer;

import java.util.List;

public class _00_libjingleTest {
    private String TAG="libjingle";

    @Test
    public void test(){
        boolean initializeAndroidGlobals = PeerConnectionFactory.initializeAndroidGlobals(
                InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext(),
                true,
                true,
                true);

//        PeerConnectionFactory peerConnectionFactory = new PeerConnectionFactory();
//        // Returns the number of camera devices
//        VideoCapturerAndroid videoCapturerAndroid = new VideoCapturerAndroid();
//        VideoCapturerAndroid.getDeviceCount();
//
//// Returns the front face device name
//        VideoCapturerAndroid.getNameOfFrontFacingDevice();
//// Returns the back facing device name
//        VideoCapturerAndroid.getNameOfBackFacingDevice();
//
//// Creates a VideoCapturerAndroid instance for the device name
//        VideoCapturerAndroid.create(name);
    }

}
