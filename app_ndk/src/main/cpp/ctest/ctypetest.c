//
// Created by anshu-pc on 2020/7/25.
//

#include <malloc.h>
#include <string.h>
#include "ctypetest.h"
void __test_type_size(){
    assert(sizeof(int) == 4);
    assert(sizeof(short int) == 2);
    assert(sizeof(long int) == 4);
    assert(sizeof(long long) == 8);
    assert(sizeof(float) == 4);
    assert(sizeof(double) == 8);
    assert(sizeof(char) == 1);
    assert(sizeof(bool) == 1);
}

//alloca;calloc;malloc,free,realloc;sbrk 底层（mmap大内存 brk小内存 munmap）
void __test_malloc(){
//malloc之后,调用函数memset来初始化这部分的内存空间
//realloc则对malloc申请的内存进行大小的调整
//free来释放
    char* p;
    p=(char*)malloc(20);
    memset(p, 0, 20 * sizeof(char));//填充为0
    strcpy(p, "hello");
    assert(p!=NULL);
    p=(char*)realloc(p,sizeof(char)*40);//重新设置，保留数据
    assert(p[0]=='h');
    strcpy(p, "hello，world");
    free(p);
}
void __test_calloc(){
//calloc()函数会返回一个由某种对象组成的数组
    char* p;
    p=(char*)calloc(20,sizeof(char));
    assert(p!=NULL);
}
void testctypesuit() {
    __test_type_size();
    __test_malloc();
    __test_calloc();
}
