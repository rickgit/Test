package edu.ptu.androidtest;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsTest {
    @Test
    public void testCopyAssets(){
        try {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            InputStream is = targetContext.getAssets().open("app-debug.apk");//Utils_dex.jar
            File file = new File(targetContext.getExternalCacheDir().getAbsolutePath() + "/app-debug.apk");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
            Assert.assertTrue(file.isFile()&&file.exists());
        } catch (IOException e) {
//            e.printStackTrace();
            Assert.fail();
        }
    }
}
