package edu.ptu.java.app_kotlin._20_ooad

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.lang.reflect.Modifier
//掩藏字段创建 var,val
//掩藏访问细节，final（ open class;abstract class,interface）,public ,private,internal。
//掩藏类信息，dataclass;nest class,inner class;
//优化枚举 ,enum class,seal class
class _10_ooad_encap {

    //相同模块内可见
    class LazyFieldClass {
        val valField: String by lazy {
            //
            "hello"
        }

        lateinit var varField: String// 不能是基本数据类型
    }

    data class DataClass(val namep: Int)//一定要定义var val

    interface IInterface//接口
    abstract class AbstractParent()

    class MClassConsNoFied constructor(nofiled: Int)//构造器
    class MClassConsHasField constructor(val nameFied: Int)//构造器




    @Test//类 ，构造器，方法，属性
    fun testRefClass() {//open class,inner class,


        //可见修饰符 private、 protected、 internal 和 public；类及方法隐式是public，属性隐式private
        //           文件可见   子类可见     模块可见（使用特殊的名字命名，防止外部引用）
        testVisiable()

        testOpenClass()//类默认是final  以下三种没加final
        testNestClass()//嵌套类对static关键字隐藏
        testInnerClass()//

        testFied()//字段隐藏，构造方法var或val
        testDataClass()//增加类的copy方法，重写tostring,hashcode,equals

        testEnumClass()
        testSealClass()//约束类的范围，相比枚举，一个类别可以有多个实例


    }

    private class MClassConsPrivate constructor(val nameFied: Int)//外部类不能访问
    class MClassConsPrivate2 private constructor(val nameFied: Int)//构造器


    @Test
    fun testFied() {

        Assert.assertEquals(MClassConsNoFied::class.java.declaredFields.size, 0)
        Assert.assertEquals(MClassConsHasField::class.java.getDeclaredField("nameFied").name, "nameFied")


        val lazyFieldClass = LazyFieldClass()
        Assert.assertEquals(lazyFieldClass.valField, "hello")


        lazyFieldClass.varField = "world"//不能是基本数据类型
        Assert.assertEquals(lazyFieldClass.varField, "world")
    }

    internal class ` internalClass`  //内部使用，特殊命名，防止外部引用

    @Test
    fun testVisiable() {
        Assert.assertEquals(MClassConsNoFied::class.java.modifiers and Modifier.PUBLIC, Modifier.PUBLIC)
        Assert.assertEquals(` internalClass`::class.java.modifiers and Modifier.PUBLIC, Modifier.PUBLIC)
        val mClassConsPrivate = MClassConsPrivate(1)//可以访问私有类
//        val mClassConsPrivate2 = MClassConsPrivate2(1)//不能访问私有构造器

    }

    @Test
    fun testInterfaceAbstractNoFinal() {
          }

    open class OpenClass

    @Test
    fun testOpenClass() {
        Assert.assertEquals(IInterface::class.java.modifiers and Modifier.FINAL, 0)
        Assert.assertEquals(AbstractParent::class.java.modifiers and Modifier.FINAL, 0)
        Assert.assertEquals(OpenClass::class.java.modifiers and Modifier.FINAL, 0)

    }


    private fun testEnumClass() {
    }

    @Test
    fun testInnerClass() {
        Assert.assertEquals(InnClass::class.java.modifiers and Modifier.STATIC, 0)
        Assert.assertEquals(InnClass::class.java.modifiers and Modifier.FINAL, Modifier.FINAL)


    }

    @Test
    fun testNestClass() {
        Assert.assertEquals(NestedClass::class.java.modifiers and Modifier.STATIC, Modifier.STATIC)
        Assert.assertEquals(NestedClass::class.java.modifiers and Modifier.FINAL, Modifier.FINAL)
    }

    inner class InnClass {//没有static修饰，可以访问外部类属性

    }

    class NestedClass {//没有static修饰，可以访问外部类属性

    }

    enum class EnumClass {
        NORTH, SOUTH, WEST, EAST
    }

    sealed class SealParent {
        class SealChild() : SealParent()
    }

    @Test
    public fun testSealClass() {
        Assert.assertFalse(SealParent::class.isAbstract)//？有问题。。。。
        Assert.assertEquals(SealParent::class.java.modifiers and Modifier.ABSTRACT, Modifier.ABSTRACT)

        when (SealParent.SealChild()) {
            is SealParent.SealChild -> println("")
        }
    }

    @Test
    fun testDataClass() {
        Assert.assertEquals(DataClass::class.java.modifiers and Modifier.FINAL, Modifier.FINAL)// Data classes cannot be abstract, open, sealed or inner;


        Assert.assertTrue(DataClass::class.java.declaredFields.size > 0)//至少一个参数，并且需要指定var或val
        Assert.assertEquals(DataClass::class.java.modifiers and Modifier.ABSTRACT, 0)// Data classes cannot be abstract, open, sealed or inner;
        Assert.assertFalse(DataClass::class.isAbstract)
        Assert.assertFalse(DataClass::class.isSealed)
        Assert.assertFalse(DataClass::class.isOpen)
        Assert.assertFalse(DataClass::class.isInner)
        Assert.assertNotEquals(DataClass::class.java.declaredMethods.filter { it.name.equals("component1") }, null)
        Assert.assertNotEquals(DataClass::class.java.declaredMethods.filter { it.name.equals("copy") }, null)
        Assert.assertNotEquals(DataClass::class.java.declaredMethods.filter { it.name.equals("hashCode") }, null)
        Assert.assertNotEquals(DataClass::class.java.declaredMethods.filter { it.name.equals("toString") }, null)
        Assert.assertNotEquals(DataClass::class.java.declaredMethods.filter { it.name.equals("equals") }, null)
    }
}

typealias TypealiasSet = MutableMap<String, MutableList<File>>

inline class InlineClass(val value: String) {
    fun printProp() {
        println("这个方法将由静态引用" + value)
    }
}