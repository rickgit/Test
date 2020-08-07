//
// Created by anshu-pc on 2020/7/28.
//
#include <assert.h>
#include "_01_asm.h"

//https://gcc.gnu.org/onlinedocs/gcc/Extended-Asm.html
void testAsmSuit(){
    int aaa=1;
    int bbbbb=1;
    int c=1;

    __asm__(
    "add %1,%0\n\t"			//这里说明下，目的操作数是后面的%0
    "mov %0,%2\n\t"			//同上，目的操作数是后面的%2
    :"=r"(aaa)                    //这一行是将汇编中的代码对应到C代码中，同样按照顺序对应%2，%3等
    :"r"(bbbbb),"r"(aaa)  //这一行是将C代码中的数据输入到汇编的代码中，按照先后顺序依次对应%0（b），%1（a）等
    );
    assert(aaa==2);
};
//寄存器
//8bit   16      32       64
//A      AX      EAX      RAX
//B
//C
//D

//a,b,c,d,S,D 分别代表 eax,ebx,ecx,edx,esi,edi 寄存器
//r 上面的寄存器的任意一个（谁闲着就用谁）
//m 内存
//i 立即数（常量，只用于输入操作数）
//g 寄存器、内存、立即数 都行（gcc你看着办）


//C/C++表达式的内联汇编格式为：
//__asm__　__volatile__("Instruction List" : Output : Input : Clobber/Modify);