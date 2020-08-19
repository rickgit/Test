package edu.ptu.androidtest._30_storage._42_file;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
//https://open.oppomobile.com/wiki/doc#id=10724
public class _001_File_android_api30 {
    public static class FileObj{
        String title;
        String name;
        String path;
    }
    public static ContentValues getCV(FileObj fileObj){
        ContentValues contentValues = new ContentValues();
        //COMPILE_SDK_VERSION=android-30
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH,fileObj.path);
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME,fileObj.name);
        contentValues.put(MediaStore.Downloads.TITLE,fileObj.title);
        return contentValues;
    }
    //add update delete
//    public static String DIRECTORY_ALARMS = "Alarms";
//    public static String DIRECTORY_AUDIOBOOKS = "Audiobooks";
//    public static String DIRECTORY_DCIM = "DCIM";
//    public static String DIRECTORY_DOCUMENTS = "Documents";
//    public static String DIRECTORY_DOWNLOADS = "Download";
//    public static String DIRECTORY_MOVIES = "Movies";
//    public static String DIRECTORY_MUSIC = "Music";
//    public static String DIRECTORY_NOTIFICATIONS = "Notifications";
//    public static String DIRECTORY_PICTURES = "Pictures";
//    public static String DIRECTORY_PODCASTS = "Podcasts";
//    public static String DIRECTORY_RINGTONES = "Ringtones";
//    public static String DIRECTORY_SCREENSHOTS = "Screenshots";

    @Test
    public void testFile() {
        ///data/data/com.android.providers.media/databases/external.db
        Uri external = MediaStore.Files.getContentUri("external");
        ContentValues contentValues = new ContentValues();

        String path = Environment.DIRECTORY_DOWNLOADS + "/newApp";
        //COMPILE_SDK_VERSION=android-30
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH,path);
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME,path);
        contentValues.put(MediaStore.Downloads.TITLE,path);

        ContentResolver contentResolver = InstrumentationRegistry.getInstrumentation().getTargetContext().getContentResolver();
        Uri insert = contentResolver.insert(external, contentValues);

        //文件
        try {
            OutputStream outputStream = contentResolver.openOutputStream(insert);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            bos.write("".getBytes());
            bos.close();
        } catch (Exception e) {
        }
    }
}
