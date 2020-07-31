package edu.ptu.java.app_kotlin

import org.junit.Assert
import org.junit.Test
import java.lang.reflect.Modifier
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
//多继承问题
class _11_ooad_extends {
    open class Parent {
        open var prop = 4;
        open fun dowork() {}
    }


    class ChildWithRef : Parent() {
        override var prop: Int
            get() = super.prop
            set(value) {}

        override fun dowork() {
            super.dowork()
        }

        inner class CInnClass {
            //没有static修饰
            fun testRfOuter() {
                super@ChildWithRef.dowork()//内部类，调用外部类的父类
            }
        }
    }

    //外部类外无法访问
    protected class ChildWithProtect constructor(fieldName: String)

    class ChildWithProtect2 protected constructor(fieldName: String)

    @Test
    fun testExtend() {
        Assert.assertNotEquals(Parent::class.java.modifiers and Modifier.STATIC, 0)
        Assert.assertEquals(Parent::class.java.modifiers and Modifier.FINAL, 0)
        Assert.assertEquals(Parent::class.java.modifiers and Modifier.ABSTRACT, 0)

        Assert.assertNotEquals(ChildWithRef::class.java.modifiers and Modifier.STATIC, 0)
        Assert.assertNotEquals(ChildWithRef::class.java.modifiers and Modifier.FINAL, 0)

        ChildWithProtect("")
    }

    interface A {
        fun foo() { print("A") }
        fun bar()
    }

    interface B {
        fun foo() { print("B") }
        fun bar() { print("bar") }
    }

    class C : A {
        override fun bar() { print("bar") }
    }
    //接口方法冲突
    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }

}