package edu.ptu.java.app_kotlin._20_ooad

import org.junit.Assert
import org.junit.Test
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

//委托方法/字段（观察者模式），合成复用原则优于继承，实现增加功能
//拓展方法（访问模式），伴生对象（访问模式），
// 内联和类型别名（内联可优化闭包）
//优化泛型（泛型是优化数组继承问题），支持类型型变，类型投影（声明泛型后，定义时可以进行投影）

/**作用域拓展函数
+---------+----------------+-------------------+-----------------+
|         | object as param|  object as return |  extension fun  |
+----------------------------------------------------------------+
|  also   |  √             |  √                |  √              |
+----------------------------------------------------------------+
|  apply  |  x             |  √                |  √              |
+----------------------------------------------------------------+
|  let    |  √             |  x                |  √              |
+----------------------------------------------------------------+
|  run    |  x             |  x                |  √              |
+----------------------------------------------------------------+
|  with   |  x             |  x                |  x              |
+---------+----------------+-------------------+-----------------+
+----------------------------------------------------------------+
|  use    |                |                   |                 |
+---------+----------------+-------------------+-----------------+
 */
class _12_ooad_dynamic_extension {


}