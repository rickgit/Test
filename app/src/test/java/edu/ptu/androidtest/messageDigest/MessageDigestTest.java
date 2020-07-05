package edu.ptu.androidtest.messageDigest;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WangAnshu on 2016/6/6.
 */
public class MessageDigestTest {

    public static final String SHA_1 = "SHA1";
    public static final String MD5 = "MD5";

    @Test
    public void testMD5() {
        try {
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            StringBuilder sb = new StringBuilder(448);
            for (int i = 0; i < 448; i++) {
                sb.append("a");
            }
            byte[] digest = null;
            String msg = sb.toString();
            byte[] msgBytes = msg.getBytes();
            byte[] bytes = msgBytes;
            long currTime = 0l;
            System.out.println("msg length " + msg.length());
            currTime = System.currentTimeMillis();
            md5.update(bytes);
            digest = md5.digest();
            System.out.println("512 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512*2
            for (int i = 0; i < 512; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*2(1k file) time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 3
            for (int i = 0; i < 512; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*3 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);


            //长度增加 512 * 4
            for (int i = 0; i < 512; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*4 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 10
            for (int i = 0; i < 512*6; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*10 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 20
            for (int i = 0; i < 512*10; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*20 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 100
            for (int i = 0; i < 512*80; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*100 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);


            //长度增加 512 * 1,000
            for (int i = 0; i < 512*900; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*1000(0.5 M file) time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 10,000
            for (int i = 0; i < 512*9000; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*10,000 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 100,000
            for (int i = 0; i < 512*90_000; i++) {
                sb.append("a");
            }
            msg = sb.toString();
            System.out.println("msg length " + msg.length());
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            md5.update(msgBytes);
            digest = md5.digest();
            System.out.println("512*100,000 time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

            //长度增加 512 * 1,000,000,stringbuilder支持不住添加,改用
            msgBytes = msg.getBytes();
            md5.reset();
            currTime = System.currentTimeMillis();
            for (int i = 0; i < 90; i++) {
                md5.update(msgBytes);
            }
            digest = md5.digest();
            System.out.println("512*1,000,000(0.25 g) time: " + (System.currentTimeMillis() - currTime) + " ms");
            printMD5(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void printMD5(byte[] digest) {
        StringBuffer buf = new StringBuffer("");
        int i;
        for (int offset = 0; offset < digest.length; offset++) {
            i = digest[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        System.out.println("===> str " + buf.toString());
    }
}
