//
// Created by anshu-pc on 2020/7/31.
//

#include <iostream>
#include <ostream>
#include <assert.h>
#include "_11_SpecialTypeTest.h"

using namespace std;

void _11_SpecialTypeTest::testReference() {
    //引用
    int i = 17;

    int &r = i;
    int *p1 = &i;
    i = 18;
    // 程序为指针变量分配内存区域，而不为引用分配内存区域;
    //指针使用时要在前加 * ，引用可以直接使用
    //没有空引用，但有空指针
    //对引用使用“sizeof”得到的是变量的大小，对指针使用“sizeof”得到的是变量的地址的大小。
    //++引用与++指针的效果不一样。
    assert((*p1) == r);
    cout << endl;
}

//
//struct tm {
//    int tm_sec;   // 秒，正常范围从 0 到 59，但允许至 61
//    int tm_min;   // 分，范围从 0 到 59
//    int tm_hour;  // 小时，范围从 0 到 23
//    int tm_mday;  // 一月中的第几天，范围从 1 到 31
//    int tm_mon;   // 月，范围从 0 到 11
//    int tm_year;  // 自 1900 年起的年数
//    int tm_wday;  // 一周中的第几天，范围从 0 到 6，从星期日算起
//    int tm_yday;  // 一年中的第几天，范围从 0 到 365，从 1 月 1 日算起
//    int tm_isdst; // 夏令时
//}
void _11_SpecialTypeTest::testDataTime() {
    time_t now = time(0);
    char *datatimeStr = ctime(&now);

    //转化为tm结构体
    tm *pTm = gmtime(&now);
    tm *lTm = localtime(&now);
}

struct person{
    string name;
    int age;
};
void structAsParams(struct person p){
    string &basicString = p.name;
    int i = p.age;
}
void _11_SpecialTypeTest::testStruct() {
    person p;
    
}

enum Type{
    ONE,TWO,OTHER
};
void _11_SpecialTypeTest::testEnum(){

}

void testDefine(){
    int i = __LINE__;
    char* f = __FILE__;
    char* d = __DATE__;
    char* t = __TIME__;
}