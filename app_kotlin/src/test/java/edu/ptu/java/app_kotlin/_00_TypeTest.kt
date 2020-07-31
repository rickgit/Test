package edu.ptu.java.app_kotlin

import android.icu.number.IntegerWidth
import org.hamcrest.core.AnyOf
import org.junit.Assert
import org.junit.Test

//增加方法类型
//类型判断与转化 is/as
//类型null
//
class _00_TypeTest { //
    @Test
    fun testType() {
        Assert.assertTrue(true is Boolean)
        Assert.assertTrue('a' is Char)

        Assert.assertTrue(0b10.toByte() is Byte)
        Assert.assertTrue(0xFF_EC_DE_5E.toByte() is Byte)
        Assert.assertTrue(10.toShort() is Short)
        Assert.assertTrue(10 is Int)
        Assert.assertTrue(3000000000 is Long)
        Assert.assertTrue(1.0f is Float)
        Assert.assertTrue(1.0 is Double)
    }

    @Test
    fun testCast() {
        Assert.assertTrue(1.toUByte() is UByte)
        Assert.assertTrue(1.toByte() is Byte)
        Assert.assertTrue(1.toUShort() is UShort)
        Assert.assertTrue(1.toShort() is Short)
        Assert.assertTrue(21_000_000_000.toUInt() is UInt)
        Assert.assertTrue(1.5f.toInt() is Int)
        Assert.assertTrue(21_000_000_000.0f.toULong() is ULong)
        Assert.assertTrue(21_000_000_000.0f.toLong() is Long)
        Assert.assertTrue(21_000_000_000.0f.toFloat() is Float)
        Assert.assertTrue(21_000_000_000.0f.toDouble() is Double)

        Assert.assertTrue(1.toChar() is Char)
        Assert.assertTrue(1.toString() is String)
    }
    @Test
    fun testArray() {
        var a = Array(5) { i -> (i * i) }
        Assert.assertTrue(a is Array<Int>)
        Assert.assertTrue(a is Array)
        Assert.assertTrue(intArrayOf(1, 2, 3) is IntArray)//int[]
        Assert.assertTrue(IntArray(1) is IntArray)//int[]
        Assert.assertTrue(IntArray(5) { 99 }[4] == 99)//int[]{99,99,99,99,99}
        Assert.assertTrue(IntArray(5) { it * 2 }[4] == 8)//int[]{99,99,99,99,99}

        //char array
        var charArr="""

        """.trimMargin()
        Assert.assertTrue(charArr=="")

        var charArrTemp="""
            |a a
        """.trimMargin()
        Assert.assertTrue(charArrTemp.equals("a a"))//| 起始符
        var name=1;
        charArrTemp="""
            |$name
        """.trimMargin()
        Assert.assertTrue(charArrTemp.equals("1"))//| 起始符


    }
    @Test
    fun testOp() {
//        Byte
        Assert.assertEquals(0b0001 and 0b0001 or 0b0010 xor 0b0000, 0b0011)
        Assert.assertEquals(0b0001 shl 2, 0b0100)//sign shift left
        Assert.assertEquals(0b1001 shl 2, 0b10_0100)//shl
        Assert.assertEquals(0b1000_0000_0000_0001 shl 2, 0b10_0000_0000_0000_0100)//shl
        Assert.assertEquals(0b1000_0000_0000_0001 shr 2, 0b10_0000_0000_0000)//shl
        Assert.assertEquals(0b1000_0000_0000_0001 ushr 2, 0b10_0000_0000_0000)//shl

        Assert.assertTrue(1 - 1.0f + 1 / 1 * 1 - 1 == 0.0f)

        //Boolean，
        Assert.assertTrue(true && false || true)
        fun hasError(x:Char)= when('a'){
            'a','b'-> print('a')
            'c'-> Assert.fail()
            else ->  Assert.fail()
        }
        for (  i in 1..3){Assert.assertTrue(i in 1..3)}

       for ((index,value) in IntArray(3){3}.withIndex()){
            Assert.assertTrue(value==3)
       }

        loop@
        for (i in 1..3)
            for (i in 3..6)
                break@loop//跳出 loop@for
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 不执行3
            print(it)
        }
    }

    @Test
    fun  testAny(){
     }


}