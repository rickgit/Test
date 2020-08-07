package edu.ptu.java.app_kotlin._10_data

import org.junit.Assert
import org.junit.Test
//集合创建
//增加区间和数列的数学概念
//增加除了增删(+,-)改查的复杂操作，搜索（获取，分组，聚合）、排序、过滤、转换
// take/drop/slice chunked window/find/random/,
//分组groupBy ,  聚合 sum，，
// order/reverse/shuffled,
//filter,map,
// 并用序列优化迭代器

class _02_collections {
    @Test
    fun testCollection() {
        var listOf = listOf<String>()
        Assert.assertTrue(listOf.javaClass.toString().equals("class kotlin.collections.EmptyList"))
        listOf = listOf<String>("")
        Assert.assertTrue(listOf.javaClass.name.equals("java.util.Collections\$SingletonList"))
        //List 的默认实现是 ArrayList
        listOf = listOf<String>("", "asd")
        Assert.assertTrue(listOf.javaClass.name.equals("java.util.Arrays\$ArrayList"))


        //Set的默认实现 - LinkedHashSet
        var of = setOf<String>()
        Assert.assertTrue(of.javaClass.toString().equals("class kotlin.collections.EmptySet"))
        of = setOf<String>("")
        Assert.assertTrue(of.javaClass.toString().equals("class java.util.Collections\$SingletonSet"))
        of = setOf<String>("", "asd")
        Assert.assertTrue(of is LinkedHashSet)
        //备选方案
        val hashSet = HashSet<String>()


        //Map 的默认实现 – LinkedHashMap
        var mapOf = mapOf<String, String>("key" to "val")
        Assert.assertTrue(mapOf.javaClass.toString().equals("class java.util.Collections\$SingletonMap"))
        mapOf = mapOf<String, String>("key" to "val", "key2" to "val2")
        Assert.assertTrue(mapOf is LinkedHashMap)

        mapOf = mutableMapOf("key" to "val", "key2" to "val2")
        Assert.assertTrue(mapOf is LinkedHashMap)

        //备选方案
        val hashMap = HashMap<String, String>()
        println()
    }

    @Test
    fun testZone() {
        var a = 1..3;//引入数学概念。区间和数列
        Assert.assertTrue(a is IntRange)
        var process = (10 downTo 2) as IntProgression;
        process = (10 downTo 2 step 2);
        Assert.assertTrue(process is IntProgression)
        Assert.assertTrue(process.first() == 10)

        //序列优化迭代器。处理流式操作，迭代器所有元素优先操作每个方法。序列每个元素先处理完所有方法操作。
        //可以避免take()时候，一些元素进行不必要的操作
        val sequenceOf = sequenceOf("2", "23")
        Assert.assertTrue(sequenceOf is Sequence)
        val listOf = listOf("23", "hello")
        listOf.asIterable().filter { it.length < 4 }.map { it.length }.take(1)//两个元素都会执行
        listOf.asSequence().//只会执行一个元素
                filter {
                    Assert.assertNotEquals(it, "hello")
                    it.length < 4 }.map { it.length
        }.take(1)

    }
}