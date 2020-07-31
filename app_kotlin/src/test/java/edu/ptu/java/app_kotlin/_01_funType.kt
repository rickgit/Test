package edu.ptu.java.app_kotlin

import org.junit.Test

class _01_funType {
    @Test
    fun testlambda(){


        //匿名函数
        fun(s: String): Int { return s.toIntOrNull() ?: 0 }
        //lambda表达式：jni 动态方法注册类似。{"sum","()v",addr}，第一个参数省略是匿名函数，第二个参数可以省略是没有返回值，
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

        // lambda 表达式中可以修改闭包中捕获的变量

        //内联函数，避免闭包，捕获外部变量，增加开销
    }
}