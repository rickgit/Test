//
// Created by anshu-pc on 2020/8/2.
//
//#if !defined(__APPLE__) && !defined(__ANDROID__)
//需要设置systemroot
#include <sys/stat.h>
#include <assert.h>
#include <stdarg.h>
//#endif

#include "_30_file_test.h"

void add(int  a,  int b ){
    assert(a+b==5);
    int ab=a+b;
    assert(a==2);
    assert(b==3);
}

void sqliteSetString(int c,  ...){
    va_list vaList;
    int countl=c;
    int num[4]={0};
    va_start(vaList,countl);//指向地址

    int index=0;
    while (index <countl ){
        num[index]=va_arg(vaList,int);//va_arg
        index++;
    }
    va_end(vaList);
    assert(sizeof(num)/ sizeof(int)==4);
}
void test_sys_state(){
    char * filepath="/data/data/edu.ptu.java.myapplication/lib/libnative-lib.so";
    struct stat statbuf;

    stat(filepath, &statbuf);
    assert(!S_ISDIR(statbuf.st_mode));
    char * pz="232\0";
    char * zFirst ="232\0";
    add(2,3 );
    int num=4;
    sqliteSetString(num,3,2,32,2);
}

