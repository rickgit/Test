package edu.ptu.javatest._60_dsa;

import org.junit.Assert;
import org.junit.Test;

public class ByteTest {
    @Test
    public void testByte() {
        Assert.assertEquals( 0x007c00,   0b00000_01111100_00000000 ) ;//MBR中的程序bootsect.S
        Assert.assertEquals( 0x090000,   0b01001_00000000_00000000 ) ;//0x90000到0x100000 64k内存
        Assert.assertEquals( 0x090200,   0b01001_00000010_00000000 ) ;//setup部分（第二扇区）拷贝到0x90200
        //1M内存
        Assert.assertEquals( 0x0a0000,   0b01010_00000000_00000000 ) ;//kernel
        //相差64k(bootsect仅占512字节，所以setup大小理论上可到63.5KB)
        Assert.assertEquals(0xa0000-0x090000,0b1_00000000_00000000);
        Assert.assertEquals(0x100000,0b10000_00000000_00000000);//高内存
//        System.out.println(Integer.toBinaryString(0x100000));
    }
}
