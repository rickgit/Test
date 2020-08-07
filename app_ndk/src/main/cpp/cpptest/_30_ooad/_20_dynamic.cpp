//
// Created by anshu-pc on 2020/7/31.
//

//多态,调用函数的对象的类型来执行不同的函数

void  testDynamic(){

}


//告诉编译器不要静态链接到该函数。
//根据所调用的对象类型来选择调用的函数，这种操作被称为动态链接，或后期绑定
class Virtual{
public:
    virtual void testMethod(){}
    virtual void testPureMethod()=0;
};
void testVirtualMethod(){

}