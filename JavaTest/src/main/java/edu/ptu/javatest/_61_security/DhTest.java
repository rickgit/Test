package edu.ptu.javatest._61_security;

import org.junit.Assert;
import org.junit.Test;

public class DhTest {
    @Test
    public void testDh(){
//        [](https://www.shuxuele.com/numbers/prime-numbers-to-10k.html)
        int bigPrim=1009;//
        int gPrim=2;//最后被公开key替换

        int expAlicePrivate=6;
        int pubKeyAlice= (int) (Math.pow(gPrim,expAlicePrivate))%bigPrim;
//        System.out.println(pubKeyAlice);
        //Alice 保留 expAlicePrivate
        //Bob 知道 bigPrim,gPrim,pubKeyAlice
        int expBobPrivate=523;
        int pubKeyBob= (int) (Math.pow(gPrim,expBobPrivate))%bigPrim;
        System.out.println("pubKeyAlice " +pubKeyAlice);
        System.out.println("pubKeyBob "+pubKeyBob);
        //clientPubKey发给服务端
        int shareKeyAlice = ((int) Math.pow(pubKeyBob, expAlicePrivate)) % bigPrim;
        int shareKeyBob = ((int) Math.pow(pubKeyAlice, expBobPrivate)) % bigPrim;
        Assert.assertEquals(shareKeyAlice,
                shareKeyBob);
        System.out.println(shareKeyAlice);
//        Assert.assertEquals(((int) Math.pow(pubKeyBob, pubKeyAlice)) % bigPrim,
//                shareKeyBob);
//
//        Assert.assertEquals(shareKeyAlice,
//                ((int)Math.pow(pubKeyAlice,pubKeyBob))%bigPrim);

        int expEvePrivate=17;
        int pubKeyEve=  ((int)Math.pow(gPrim,expEvePrivate))%bigPrim;
        System.out.println("pubKeyEve "+pubKeyEve);
        int shareKeyEve = ((int) Math.pow(pubKeyAlice, expEvePrivate)) % bigPrim;
        Assert.assertEquals(shareKeyEve,
                shareKeyAlice);


    }
    @Test
    public void test(){
        int bigPrim=23;//
        int gPrim=5;//最后被公开key替换
        for (int privateKey = 0; privateKey < 13; privateKey++) {
            System.out.println( ( (int)Math.pow(gPrim,privateKey))%bigPrim);
        }
//        System.out.println(Math.pow(5,6)%23);
//        System.out.println(Math.pow(5,15)%23);
    }
}
