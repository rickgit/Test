package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    @Test
    public void testHash(){
        //10011001111         1231
//10011010101  12137
        Assert.assertEquals(new Boolean(true).hashCode(),1231);// 质数，避免冲突；如果true和false是1，0冲突的概率很大  0b100_11001111
        Assert.assertEquals(new Boolean(false).hashCode(),1237);//质数，避免冲突 0b100_11010101
        Assert.assertEquals(new Byte("11").hashCode(),11);//

        Assert.assertEquals(new Character('1').hashCode(),'1');//
        Assert.assertEquals(new Short("111").hashCode(),111);//

        Assert.assertEquals(new Integer(1).hashCode(),1);
        Assert.assertEquals(new Float(1.5).hashCode(), 0b0_01111111_10000000000000000000000 );//规范化 1.1(2)；转化为IEEE-754
        Assert.assertEquals(new Long(111111111111111111l).hashCode(), (int)(111111111111111111l ^ (111111111111111111l >>> 32)) );
        Assert.assertEquals(new Double(1.5d).hashCode(), 0b0_01111111111_10000000000000000000  );//规范化 1.1(2)；转化为IEEE-754
//        System.out.printf(new Integer(0b0_01111111111_10000000000000000000).toString());
        Assert.assertEquals("1".hashCode(),strHash("1"));//保证单个自然数，和整性hash值一致
        Assert.assertEquals("1".hashCode(), '1');
        Assert.assertNotEquals("1.0".hashCode(), new Float(1.0).hashCode());
        Assert.assertEquals("12".hashCode(),(int)('1'*31+'2'));
        Assert.assertEquals("12".hashCode(),(int)(('1'<<5)-'1'+'2'));

        //HashMap key hash
        //浮点型容易hash冲突 (SEEEEEEEEMMMMMMMMMMMMMMMMMMMMMMM)
        // (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); 低位有值，需要SEEEEEEEEMMMMMM，M都为0的话，M占6位，1<7求余才不为0
        Assert.assertEquals(1<<7,128);
        Assert.assertEquals((new Float(1.5).hashCode()^(new Float(1.5).hashCode() >>> 16))%(1<<7),64);
        Assert.assertEquals(new Float(1.5).hashCode()%16,0);
        Assert.assertEquals(new Float(1.25).hashCode()%16,0);
        Assert.assertEquals((new Float(1.5).hashCode()^(new Float(1.5).hashCode() >>> 16))%8,0);
        Assert.assertEquals((new Float(1.25).hashCode()^(new Float(1.25).hashCode() >>> 16))%8,0);
        //HashTable  key.value%0b01111111111111111111111111111111

        //Hashmap 高效率，泊松分布保证避免tab里的值冲突；HashTable 线程安全，tab长度为质数或奇数，tab分散分布，避免哈希冲突
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
