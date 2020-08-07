package edu.ptu.java.app_kotlin._00_ooad

import org.junit.Assert
import org.junit.Test

//拓展Java泛型。声明处型变（declaration-site variance）与类型投影（type projections）。
class _13_ooad_generic {

    interface ProductorGeneric<out T> {
        fun nextT(): T
    }

    interface ConsumerGeneric<in T> {
        operator fun compareTo(other: T): Int
    }

    @Test
    fun testGenericVariance() {
        //ArrayList<String> o=new ArrayList<>()
        //ArrayList<Object> oo=o;//编译不会报错，但java 不允许这样写，第三步会出问题
        //oo.add(1);
        //List<String> 并不是 List<Object> 的子类型，Java是不可变(invariant)。kotlin是可变的（variance   ）

        // <out t> 生产者 ；<in t>只能消费
//        PECS 代表生产者-Extens，消费者-Super（Producer-Extends, Consumer-Super）。
        val arrayList = object : ProductorGeneric<String> {
            //undefinedtype
            override fun nextT(): String {
                return "hello"
            }
        }
        val obj: ProductorGeneric<Any> = arrayList//只作为生产者，可以赋值泛型参数父类
        Assert.assertEquals(obj, arrayList)
        val consumerObj = object : ConsumerGeneric<Any> {
            override fun compareTo(other: Any): Int {
                return 1
            }


        }
        val consumerObj2: ConsumerGeneric<String> = consumerObj//只作为消费者，可以赋值泛型参数子类

        //泛型类即使生产者，又是消费者，不用in,out。考虑类型投影
    }

    @Test//泛型类即使生产者，又是消费者。在定义变量时，根据生产者，消费者进行投影，
    fun testGenericTypeProj() {
        //定义类：class Array<T>(val size: Int)
        //声明形参：fun copy(from: Array<out Any>, to: Array<Any>)
        //声明形参：fun fill(dest: Array<in String>, value: String)

        ///星投影 in,out的极端情况
//       声明类 Function <in T, out U>，我们可以想象以下星投影：
//      定义变量/常量：
//        Function<*, String> 表示 Function<in Nothing, String>；
//        Function<Int, *> 表示 Function<Int, out Any?>；
//        Function<*, *> 表示 Function<in Nothing, out Any?>
    }

    @Test
    fun testErase(){
//        Foo<Bar> 与 Foo<Baz?> 的实例都会被擦除为 Foo<*>
    }
}