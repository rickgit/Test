package edu.ptu.androidutils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.ACTIVITY_SERVICE;

public class PhoneInfo {
    public static ArrayList<String> getPhoneOsInfo(Activity context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        ArrayList<String> infos = new ArrayList<>();
        infos.add("imei: " + mTm.getDeviceId());//得到用户Id
        infos.add("imsi: " + mTm.getSubscriberId());
        infos.add("deviceid: " + mTm.getDeviceId());//获取智能设备唯一编号
        infos.add("te1: " + mTm.getLine1Number());//获取本机号码
        infos.add("simSerialNumber: " + mTm.getSimSerialNumber());//获得SIM卡的序号
        infos.add("numer: " + mTm.getLine1Number());// 手机号码，有的可得，有的不可得

        WifiInfo wifiInfo = ((WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
        infos.add("Mac: " + wifiInfo.getMacAddress());//MAC地址

        infos.add("手机型号: " + android.os.Build.MODEL);// 手机型号
        infos.add("Product: " + android.os.Build.PRODUCT);
        infos.add("CPU_ABI: " + android.os.Build.CPU_ABI);
        infos.add("TAGS: " + android.os.Build.TAGS);
        infos.add("VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE);
        infos.add("SDK: " + android.os.Build.VERSION.SDK);
        infos.add("VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE);
        infos.add("DEVICE: " + android.os.Build.DEVICE);
        infos.add("DISPLAY: " + android.os.Build.DISPLAY);
        infos.add("BRAND: " + android.os.Build.BRAND);
        infos.add("BOARD: " + android.os.Build.BOARD);
        infos.add("FINGERPRINT: " + android.os.Build.FINGERPRINT);
        infos.add("ID: " + android.os.Build.ID);
        infos.add("MANUFACTURER: " + android.os.Build.MANUFACTURER);
        infos.add("USER: " + android.os.Build.USER);

        return infos;
    }

    //手机CPU信息

    public static String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};  //1-cpu型号  //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return cpuInfo;
    }

    public static int getCPUMaxFreqKHz() {
        int maxFreq = -1;
        try {
            for (int i = 0; i < getNumberOfCPUCores(); i++) {
                String filename =
                        "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
                File cpuInfoMaxFreqFile = new File(filename);
                if (cpuInfoMaxFreqFile.exists()) {
                    byte[] buffer = new byte[128];
                    FileInputStream stream = new FileInputStream(cpuInfoMaxFreqFile);
                    try {
                        stream.read(buffer);
                        int endIndex = 0;
                        //Trim the first number out of the byte buffer.
                        while (buffer[endIndex] >= '0' && buffer[endIndex] <= '9'
                                && endIndex < buffer.length) endIndex++;
                        String str = new String(buffer, 0, endIndex);
                        Integer freqBound = Integer.parseInt(str);
                        if (freqBound > maxFreq) maxFreq = freqBound;
                    } catch (NumberFormatException e) {
                        //Fall through and use /proc/cpuinfo.
                    } finally {
                        stream.close();
                    }
                }
            }
            if (maxFreq == -1) {
                FileInputStream stream = new FileInputStream("/proc/cpuinfo");
                try {
                    int freqBound = parseFileForValue("cpu MHz", stream);
                    freqBound *= 1000; //MHz -> kHz
                    if (freqBound > maxFreq) maxFreq = freqBound;
                } finally {
                    stream.close();
                }
            }
        } catch (IOException e) {
            maxFreq = -1; //Fall through and return unknown.
        }
        return maxFreq;
    }

    private static int parseFileForValue(String cpu_mHz, FileInputStream stream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String temp = null;
        while (true) {
            try {
                if (((temp = bufferedReader.readLine()) == null)) break;
                System.out.println(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static long getTotalMemory(Context c) {
        // memInfo.totalMem not supported in pre-Jelly Bean APIs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
            ActivityManager am = (ActivityManager) c.getSystemService(ACTIVITY_SERVICE);
            am.getMemoryInfo(memInfo);
            if (memInfo != null) {
                return memInfo.totalMem;
            } else {
                return -1;
            }
        } else {
            long totalMem = -1;
            try {
                FileInputStream stream = new FileInputStream("/proc/meminfo");
                try {
                    totalMem = parseFileForValue("MemTotal", stream);
                    totalMem *= 1024;
                } finally {
                    stream.close();
                }
            } catch (IOException e) {
            }
            return totalMem;
        }
    }
    public static ArrayList<String>  getAppMemory(Context context){
        ArrayList<String> infos = new ArrayList<>();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        //最大分配内存
        int memory = activityManager.getMemoryClass();
        infos.add("memory: "+memory);
        float LargeMemory = (float) (activityManager.getLargeMemoryClass() );
        infos.add("largeMemory: "+LargeMemory);
        //最大分配内存获取方法2
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0/ (1024 * 1024));
        //当前分配的总内存
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0/ (1024 * 1024));
        //剩余内存
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0/ (1024 * 1024));
        infos.add("maxMemory: "+maxMemory);
        infos.add("totalMemory: "+totalMemory);
        infos.add("freeMemory: "+freeMemory);

        return infos;
    }

    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            // Gingerbread doesn't support giving a single application access to both cores, but a
            // handful of devices (Atrix 4G and Droid X2 for example) were released with a dual-core
            // chipset and Gingerbread; that can let an app in the background run without impacting
            // the foreground application. But for our purposes, it makes them single core.
            return 1;
        }
        int cores;
        try {
            cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (SecurityException e) {
            cores = -1;
        } catch (NullPointerException e) {
            cores = -1;
        }
        return cores;
    }

    private static final FileFilter CPU_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getName();
            //regex is slow, so checking char by char.
            if (path.startsWith("cpu")) {
                for (int i = 3; i < path.length(); i++) {
                    if (path.charAt(i) < '0' || path.charAt(i) > '9') {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    };

}
