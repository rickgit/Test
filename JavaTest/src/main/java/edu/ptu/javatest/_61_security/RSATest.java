package edu.ptu.javatest._61_security;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

//import sun.security.jca.JCAUtil;

public class RSATest {
    @Test
    public void testRSA() {
        //
        BigInteger p = BigInteger.valueOf(17);
        BigInteger q = BigInteger.valueOf(11);

//        testPrim();
    }

    @Test
    public void testPrim() {
//        SecureRandom secureRandom = JCAUtil.getSecureRandom();
//        int keySize = 128;
//        int lp = (keySize + 1) >> 1;
//        int lq = keySize - lp;
//        long t=System.nanoTime();
//        BigInteger primP = BigInteger.probablePrime(lp, secureRandom);
//        System.out.println("质数 计算量 "+(System.nanoTime()-t));//540,470,100 ns
//        System.out.println(primP);
//        BigInteger primeQ = BigInteger.probablePrime(lq, secureRandom);
//        System.out.println(primeQ);
//        if (primP.compareTo(primeQ)<0){
//            BigInteger temp=primP;
//            primP=primeQ;
//            primeQ=temp;
//        }
//        Assert.assertTrue(primP.compareTo(primeQ)>0);//p大于q
//        BigInteger n=primP.multiply(primeQ);
//        System.out.println(n.bitLength());
//        System.out.println(n.bitLength()<keySize);//长度小于keySize
//
//        BigInteger phi = primP.subtract(BigInteger.ONE).multiply(primeQ.subtract(BigInteger.ONE));//欧拉函数，计算n含有互质整数数量
//        BigInteger e=BigInteger.valueOf(13);
//        System.out.println(  "phi "+phi);
////        System.out.println(phi.compareTo(e)<0);
//        t=System.nanoTime();
//        System.out.println( e.gcd(phi).equals(BigInteger.ONE));
//        System.out.println("最大公约数 计算量 "+(System.nanoTime()-t));//384600 ns
//
//        t=System.nanoTime();
//        BigInteger d = e.modInverse(phi);//模逆元：e*d % phi=1，求出d
//        System.out.println("模逆元 计算量 "+(System.nanoTime()-t));//384600 ns
//        t=System.currentTimeMillis();
//        BigInteger pe = d.mod(primP.subtract(BigInteger.ONE));
//        BigInteger qe = d.mod(primeQ.subtract(BigInteger.ONE));
//        BigInteger coeff = primeQ.modInverse(primP);
//
////        PublicKey publicKey = new RSAPublicKeyImpl(n, e);
////        PrivateKey privateKey =
////                new RSAPrivateCrtKeyImpl(n, e, d, p, q, pe, qe, coeff);
////        return new KeyPair(publicKey, privateKey);
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextInt(23);
        System.out.println();
    }
}
