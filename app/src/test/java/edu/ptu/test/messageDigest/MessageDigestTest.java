package edu.ptu.test.messageDigest;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WangAnshu on 2016/6/6.
 */
public class MessageDigestTest {
    @Test
    public void test(){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = "来′".getBytes();
            System.out.println("===>"+bytes.length);
            md5.update(bytes);
            byte[] digest = md5.digest();
            System.out.println("===> result "+digest.length);//128位bit， 16个字节，32个16进制字符
            System.out.println("===> result "+new String(digest));
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < bytes.length; offset++) {
                i = bytes[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            System.out.println("===> str "+buf.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
}
