//
// Created by anshu-pc on 2020/7/28.
//
#include <assert.h>
#include "_01_asm.h"
//#include <stdio.h>

void testAsmSuit(){
    int a=1;
    int b=1;
    int c=1;

    __asm__(
    "add %1,%0\n\t"			//这里说明下，目的操作数是后面的%0
    "mov %0,%2\n\t"			//同上，目的操作数是后面的%2
    :"=r"(a)                    //这一行是将汇编中的代码对应到C代码中，同样按照顺序对应%2，%3等
    :"r"(b),"r"(a)  //这一行是将C代码中的数据输入到汇编的代码中，按照先后顺序依次对应%0（b），%1（a）等
    );
    assert(a==2);
};
//寄存器
//8bit   16      32       64
//A      AX      EAX      RAX
//B
//C
//D