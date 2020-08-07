package edu.ptu.java.app_kotlin._00_ooad

import org.junit.Assert
import org.junit.Test
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun StringBuffer.getPackageExtension() {//
    println("包下面生成 工具类方法 _12_ooad_dynamicKt")
}
//委托方法/字段（观察者模式），合成复用原则优于继承，实现增加功能
//拓展方法（访问模式），伴生对象（访问模式），
// 内联和类型别名（内联可优化闭包）
//优化泛型（泛型是优化数组继承问题），支持类型型变，类型投影（声明泛型后，定义时可以进行投影）

class _10_ooad_dynamic {
    fun StringBuffer.getClassExtension() {
        println("类下面生成 工具类方法")
    }

    val StringBuffer.lastIndexCustom: Int
        get() = length - 1


    @Test
    fun testExtension() {//类里定义拓展方法 访问者模式。文件顶层定义，包作用域，创建个工具类，静态方法访问
        StringBuffer().getClassExtension()
        StringBuffer().getPackageExtension()
        println(StringBuffer().lastIndexCustom)//不能由初始化
    }

    companion object {
        fun create(): _10_ooad_dynamic = _10_ooad_dynamic()
    }

    @Test
    fun testCompanionObj() {
        val create = create()//默认生成Companion
        val didrectCreate = create()//可以直接调用
    }


    //    testInlineClass()//相当于 #define
//    testTypealias()//相当于 #typedef
    @Test
    fun testTypealias() {
        //TypealiasSet
        Assert.assertEquals(TypealiasSet::javaClass.javaClass.enclosingClass, null) //inline 必须是顶级类
    }

    @Test
    fun testInlineClass() {
        Assert.assertEquals(InlineClass::class.java.enclosingClass, null) //inline 必须是顶级类
        Assert.assertNotEquals(InlineClass::class.java.declaredMethods.size, 1)//必须有且只有一个
        InlineClass("value").printProp()//inline相当于C语言宏，替换应用到的地方.inline 类的方法将被静态引用
    }

    interface Base {
        fun dowork()
        fun doSpecial(): Boolean
    }

    class DelegationHelpClass : Base {
        override fun dowork() {
            println("do DelegationHelpClass")
        }

        override fun doSpecial(): Boolean {
            return false
        }
    }

    class FieldDelegationHelp {
        var delegateFieldStore: String = "hello"

        operator fun getValue(userImplClass: UserImplClass, property: KProperty<*>): String {

            return delegateFieldStore
        }

        operator fun setValue(userImplClass: UserImplClass, property: KProperty<*>, s: String) {
            delegateFieldStore = s;

        }

    }

    class UserImplClass(b: DelegationHelpClass) : Base by b {
        var nameField: String by FieldDelegationHelp()
        override fun doSpecial(): Boolean {
            return true
        }
    }

    class DelegateLazyFieldClass {
        val lazyField: String by lazy {
            "hello"
        }


    }

    class DelegateObservableFieldClass {

        var observableField: String by Delegates.observable("hello") { property, oldValue, newValue ->
            println("$oldValue -> $newValue")
        }
    }

    @Test
    fun testDelegationFun() {//替代继承，里氏替换原则
        val userImplClass = UserImplClass(DelegationHelpClass())
        userImplClass.dowork()
        Assert.assertEquals(userImplClass.doSpecial(), true)//子类有重写，就不用委托方法

        //委托字段
        Assert.assertEquals(userImplClass.nameField, "hello")
        userImplClass.nameField = "ni hao shi jie"
        Assert.assertEquals(userImplClass.nameField, "ni hao shi jie")
        //lazy
        Assert.assertEquals(DelegateLazyFieldClass().lazyField, "hello")
        //观察字段
        val delegateObservableFieldClass = DelegateObservableFieldClass()
        Assert.assertEquals(delegateObservableFieldClass.observableField, "hello")
        delegateObservableFieldClass.observableField = "world"

    }

}