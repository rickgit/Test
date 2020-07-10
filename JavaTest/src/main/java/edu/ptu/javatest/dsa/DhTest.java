package edu.ptu.javatest.dsa;

import org.junit.Assert;
import org.junit.Test;

public class DhTest {
    @Test
    public void testDh(){
        int bigPrim=97;//
        int gPrim=3;//最后被公开key替换

        int serverPrivate=6;
        int serverPubKey= (int) (Math.pow(gPrim,serverPrivate))%bigPrim;
        System.out.println(serverPubKey);
        //server 保留 serverPrivate
        //client 知道 bigPrim,gPrim,ServerToClient
        int clientPrivate=21;
        int clientPubKey= (int) (Math.pow(gPrim,clientPrivate))%bigPrim;
        System.out.println(clientPubKey);
        //clientPubKey发给服务端
        Assert.assertEquals(((int)Math.pow(clientPubKey,serverPrivate))%bigPrim,
                ((int)Math.pow(serverPubKey,clientPrivate))%bigPrim);


        Assert.assertEquals(((int)Math.pow(clientPubKey,serverPubKey))%bigPrim,
                ((int)Math.pow(serverPubKey,clientPrivate))%bigPrim);

        Assert.assertEquals(((int)Math.pow(clientPubKey,serverPrivate))%bigPrim,
                ((int)Math.pow(serverPubKey,clientPubKey))%bigPrim);
    }
}
