//
// Created by anshu-pc on 2020/7/28.
//

#include "_02_storage.h"
//auto extern register static const
static int a;
static int bb;
void testStoreRegister(){
    //auto 局部变量，自动分配内存，自动释放
    //static 局部变量初始化为0，只有第一次初始化有效，只能用常量初始化
    // 全局变量，找不到全局变量的声明时，用extern声明，但不能赋值。
    //全局变量static 和普通局部变量static区别是作用域不同。全局变量static只能在当前文件使用


}

void testMemoryLayout(){
    //使用 size命令；text,data（初始化数据，全局变量，static变量，文字常量区）,bss（没有初始化的数据，全局变量，static变量）
    //stack，普通局部变量，自动管理内存；heap，手动申请，手动释放，程序结束，自动回收。
    //ulimit -a;查看栈的大小
}
void testMemory(){
    //memset,memcpy,memmove,memcmp
}

void testHeap(){
    //heap，free
}
void testStorageSuit(){
    testStoreRegister();
}