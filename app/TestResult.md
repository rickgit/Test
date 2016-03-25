##Annotation 测试用例
测试用例 *edu.ptu.test.performace.AnotationTest*
| 用时，单位毫秒（ns） | 操作 |
| ------------- |:-------------:|
| 20378875 |  对象初始化 |
| 3925 | 获取属性值 |
| 3622 | 获取类 TYPE 的注解 |

##init 对象初始化耗时
- for循环创建Dog对象 -> 再for循环创建Dog的时间 小于 for循环创建Dog对象 -> 再for循环创建Cat的时间 
 再次循环时，用list将对象加入容器，保证不被回收，但结果还是一样的，具体还需要查看java sdk源代码的对象创建。
 
##reflect 
```java
public class Cat {
    public static void main(String[] args) {
        try {
            Cat cat = Cat.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

//非反射代码
public class Cat {
    public static void main(String[] args) {
        try {
            Cat cat = new Cat();
        } catch (Exception e) {
        }
    }
}
```
编译成bytecode的代码
```asm
    Code:
      stack=1, locals=2, args_size=1
         0: ldc_w         #2                  // class edu/ptu/test/bean/Cat
         3: invokevirtual #3                  // Method java/lang/Class.newInstance:()Ljava/lang/Object;
         6: checkcast     #2                  // class edu/ptu/test/bean/Cat
         9: astore_1      
        10: goto          26
        13: astore_1      
        14: aload_1       
        15: invokevirtual #5                  // Method java/lang/InstantiationException.printStackTrace:()V
        18: goto          26
        21: astore_1      
        22: aload_1       
        23: invokevirtual #7                  // Method java/lang/IllegalAccessException.printStackTrace:()V
        26: return        
      Exception table:
         from    to  target type
             0    10    13   Class java/lang/InstantiationException
             0    10    21   Class java/lang/IllegalAccessException
             
##dex 
methods[1]:
  access_flags: public|static
  name: main
  descriptor: ([Ljava/lang/String;)V
  attributes_count: 0001
  
  attributes[0]:
    name: Code
    length: 00000048
    max_stack: 0001
    max_locals: 0002
    code_length: 0000000f
    0000: ldc_w type{edu.ptu.test.bean.Cat}
    0003: invokevirtual method{java.lang.Class.newInstance:()Ljava/lang/Object;
    }
    0006: checkcast type{edu.ptu.test.bean.Cat}
    0009: astore_1 // 01
    000a: goto 000e
    000d: astore_1 // 01
    000e: return
    exception_table_length: 0001
      0000..000a -> 000d java.lang.Exception
    attributes_count: 0002
    
    attributes[0]:
      name: LineNumberTable
      length: 00000012
      line_number_table_length: 0004
      0000 9
      000a 11
      000d 10
      000e 12
    end attributes[0]
    
    attributes[1]:
      name: StackMapTable
      length: 00000007
      attribute data
    end attributes[1]
  end attributes[0]
end methods[1]
```
非反射的代码
```asm
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class edu/ptu/test/bean/Cat
         3: dup           
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1      
         8: goto          12
        11: astore_1      
        12: return        
      Exception table:
         from    to  target type
             0     8    11   Class java/lang/Exception
#dex
methods[1]:
  access_flags: public|static
  name: main
  descriptor: ([Ljava/lang/String;)V
  attributes_count: 0001
  
  attributes[0]:
    name: Code
    length: 00000046
    max_stack: 0002
    max_locals: 0002
    code_length: 0000000d
    0000: new type{edu.ptu.test.bean.Cat}
    0003: dup
    0004: invokespecial method{edu.ptu.test.bean.Cat.<init>:()V}
    0007: astore_1 // 01
    0008: goto 000c
    000b: astore_1 // 01
    000c: return
    exception_table_length: 0001
      0000..0008 -> 000b java.lang.Exception
    attributes_count: 0002
    
    attributes[0]:
      name: LineNumberTable
      length: 00000012
      line_number_table_length: 0004
      0000 9
      0008 11
      000b 10
      000c 12
    end attributes[0]
    
    attributes[1]:
      name: StackMapTable
      length: 00000007
      attribute data
    end attributes[1]
  end attributes[0]
end methods[1]

```
###setAccessible(true) 效率会提升

