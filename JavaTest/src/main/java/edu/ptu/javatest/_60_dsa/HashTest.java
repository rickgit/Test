package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    @Test
    public void testHash(){
        //10011001111         1231
//10011010101  12137
        Assert.assertEquals(new Boolean(true).hashCode(),1231);// 质数，避免冲突；如果true和false是1，0冲突的概率很大
        Assert.assertEquals(new Boolean(false).hashCode(),1237);//质数，避免冲突
        Assert.assertEquals(new Byte("11").hashCode(),11);//

        Assert.assertEquals(new Character('1').hashCode(),'1');//
        Assert.assertEquals(new Short("111").hashCode(),111);//

        Assert.assertEquals(new Integer(1).hashCode(),1);
        Assert.assertEquals(new Float(1.5).hashCode(), 0b0_01111111_10000000000000000000000 );//规范化 1.1(2)；转化为IEEE-754
        Assert.assertEquals(new Long(111111111111111111l).hashCode(), (int)(111111111111111111l ^ (111111111111111111l >>> 32)) );
        Assert.assertEquals(new Double(1.5d).hashCode(), 0b0_01111111111_10000000000000000000  );//规范化 1.1(2)；转化为IEEE-754
        System.out.printf(new Integer(0b0_01111111111_10000000000000000000).toString());
        Assert.assertEquals("1".hashCode(),strHash("1"));
        Assert.assertEquals("1".hashCode(), '1');
        Assert.assertEquals("12".hashCode(),(int)('1'*31+'2'));
        Assert.assertEquals("12".hashCode(),(int)(('1'<<5)-'1'+'2'));
    }

    public int strHash(String str){
        int len=str.length();
        int h=0;
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(i);
        }
        return h;
    }
}
