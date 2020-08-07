//
// Created by anshu-pc on 2020/7/31.
//
#include <limits>
#include <stdio.h>
#include <iostream>
#include "_10_DataTypeTest.h"
using namespace std;

void testSizeBool() {
    size_t size = sizeof(bool); __glibcxx_assert(size==1)
    int max = (numeric_limits<bool>::max)();
    int min  = (numeric_limits<bool>::min)();
    cout<<endl;
}


void testSizechar() {
    size_t size = sizeof(char); __glibcxx_assert(size==1)
    int max = (numeric_limits<char>::max)();
    int min = (numeric_limits<char>::min)();
    cout<<endl;
}
void testSizewchar() {
    size_t size = sizeof(wchar_t); __glibcxx_assert(size==4)
    int max = (numeric_limits<wchar_t>::max)();
    int min = (numeric_limits<wchar_t>::min)();
    cout<<endl;
}
void testSizeFloat() {
    size_t size = sizeof(float); __glibcxx_assert(size==4)
    int max = (numeric_limits<float>::max)();
    int min  = (numeric_limits<float>::min)();
    cout<<endl;
}
void testSizeint() {
    size_t size = sizeof(int); __glibcxx_assert(size==4)
    int max = (numeric_limits<int>::max)();
    int min  = (numeric_limits<int>::min)();
    cout<<endl;
}

void testSizeDouble() {
    size_t size = sizeof(double); __glibcxx_assert(size==8)
    int max = (numeric_limits<double>::max)();
    int min  = (numeric_limits<double>::min)();
    cout<<endl;
}
void testSevenTypeSize(){
    testSizeBool();
    testSizechar();
    testSizewchar();

    testSizeint();
    testSizeFloat();
    testSizeDouble();

    //void
}

void _10_DataTypeTest::testSevenTypeSize(){
    testSizeBool();
    testSizechar();
    testSizewchar();

    testSizeint();
    testSizeFloat();
    testSizeDouble();

    //void
}