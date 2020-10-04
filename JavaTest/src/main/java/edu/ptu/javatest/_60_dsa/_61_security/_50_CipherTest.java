package edu.ptu.javatest._60_dsa._61_security;

import org.junit.Test;

import java.security.DigestException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import edu.ptu.javatest._20_ooad._50_dynamic._00_ReflectionTest;

public class _50_CipherTest {
    @Test
    public void testDsa() throws Exception {
        byte[] seed = "seed".getBytes();//
        byte[] byteContent = "content".getBytes("utf-8");//需要注意加密/解密的编码要一样

        KeyGenerator kgen = KeyGenerator.getInstance("AES");//算法
        kgen.init(128, new SecureRandom(seed));//密钥长度；  通过传入 SecureRandom的seed，经 SHA-1 伪随机器（不同平台而异），生成 128 bit密钥
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();

        Cipher cipher = Cipher.getInstance("AES");//加密算法名，windows 默认 "AES/ECB/PKCS5Padding"
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));//传入 密钥字节，选择加密实现。init 从 provider挑选实现类，并初始化 spi
        byte[] result = cipher.doFinal(byteContent);
        System.out.println(Arrays.toString(result));

    }

    @Test//rt.jar/security
    public void testMessageDigite() throws Exception {

        byte[] byteContent = "content".getBytes("utf-8");//需要注意加密/解密的编码要一样

        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(byteContent);
        byte[] digest = md5.digest();
        System.out.println(String.valueOf(digest));
    }


    @Test//rt.jar/security
    public void sign() throws Exception {
        String content = "content";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //1. MD/SHA/PIPEMD+RSA；
        // 2. SHA+DSA；
        //3. NONE/PIPEMD/SHA+ECDSA
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(
                KeyFactory.getInstance("RSA")
                        .generatePrivate(
                                new PKCS8EncodedKeySpec(
                                        keyPair.getPrivate().getEncoded()//Alice 负责签名
                                )
                        )
        );
        signature.update(content.getBytes());
        byte[] signResult = signature.sign();//摘要加密后的签名


        signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(
                KeyFactory.getInstance("RSA")
                        .generatePublic(
                                new X509EncodedKeySpec(
                                        keyPair.getPublic().getEncoded()//Bob 只能签名获取摘要，不能重新摘要签名
                                )
                        )
        );
        signature.update(content.getBytes());
        boolean bool = signature.verify(signResult);//验证Alice签名，解密Alice签名获取到摘要，再用Bob的摘要判断是否相等
    }

    @Test//jce.jar crypto
    public void hmac() throws Exception {
        byte[] shareKeyMd5Salt = "key".getBytes();//salt和content 一起摘要
        byte[] byteContent = "content".getBytes("utf-8");//需要注意加密/解密的编码要一样
        SecretKeySpec shareKey = new SecretKeySpec(shareKeyMd5Salt, "HmacMD5");
        Mac mac = Mac.getInstance(shareKey.getAlgorithm());//其他 HmacSHA1, HmacSHA256
        mac.init(shareKey);//MD5加salt，和内容一起签名，见testHmacMD5() 或src\java.base\share\classes\com\sun\crypto\provider\HmacCore.java
        byte[] result = mac.doFinal(byteContent);//
        System.out.println(String.valueOf(result));
    }

    @Test
    //HMAC（K，M）=H（K⊕opad∣H（K⊕ipad∣M））
    public void testHmacMD5() throws Exception {
        byte[] md5SaltShareKey = "key".getBytes();//salt和content 一起摘要
        byte[] k_ipad = new byte[64];
        byte[] k_opad = new byte[64];
        for (int i = 0; i < 64; i++) {
            int si = (i < md5SaltShareKey.length) ? md5SaltShareKey[i] : 0;
            k_ipad[i] = (byte) (si ^ 0x36);
            k_opad[i] = (byte) (si ^ 0x5c);
        }

        byte[] byteContent = "content".getBytes("utf-8");//需要注意加密/解密的编码要一样

        MessageDigest md5 = MessageDigest.getInstance("md5");

        ////////////////////// init
        boolean first = true;
        if (first == true) {
            // compute digest for 1st pass; start with inner pad
            md5.update(k_ipad);
            first = false;
        }
        ////////////////////// update
        md5.update(byteContent);

        ////////////////////// dofinal
        byte[] temp = md5.digest();
        // compute digest for 2nd pass; start with outer pad
        md5.update(k_opad);
        // add result of 1st hash
        md5.update(temp);
        int digest = md5.digest(temp, 0, temp.length);
        System.out.println(temp);

    }

}
