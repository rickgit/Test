//
// Created by anshu-pc on 2020/7/25.
//

#include <malloc.h>
#include <string.h>
#include <stdlib.h>
#include "_00_ctypetest.h"

// int     a    =1;
//数据类型 标识符=变量/常量;
void __test_type_size() {
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
void __test_malloc() {
//malloc之后,调用函数memset来初始化这部分的内存空间
//realloc则对malloc申请的内存进行大小的调整
//free来释放
    char *p;
    p = (char *) malloc(20);
    memset(p, 0, 20 * sizeof(char));//填充为0
    strcpy(p, "hello");
    assert(p != NULL);
    p = (char *) realloc(p, sizeof(char) * 40);//重新设置，保留数据
    assert(p[0] == 'h');
    strcpy(p, "hello，world");
    free(p);
}

void __test_calloc() {
//calloc()函数会返回一个由某种对象组成的数组
    char *p;
    p = (char *) calloc(20, sizeof(char));
    assert(p != NULL);
}

#define MValue 23

void __testMacro() {
    int value = MValue;
    assert(value == 23);//宏在编译时候，替换宏的标识符
    assert(1);

}

void __testArray() {
    //不初始化，值为随机数
    int arr[10];
    int arr2[2] = {1, 2};
    //自动初始化
    int arr3[3] = {1};
    assert(arr3[1] == 0);

    int arr4[] = {0, 12, 3, 4};

    //数组名是首元素地址
    assert(*arr4 == 0);
    //元素个数 size
    size_t sizeArr4 = sizeof(arr4) / sizeof(arr4[0]);
    assert(sizeArr4 == 4);

    //多维数组，本质是一维数组组成，多少维度，多少个方括号。
    //UNICODE[256][256][17] 256行，256列，17块
    int unicode[256][256][17];
    unicode[0][0][0] = 1;
    // 看起来方便
    int unicode2[2][2] = {{1, 3},
                          {3, 4}};
    assert(unicode2[0][0] == 1);
    //一维数组的初始化方法。有初始化时候，默认0
    int unicode3[2][2] = {1, 2, 3, 4};
    //第一个[]可以不写
    int unicode4[][2] = {1, 2, 3, 4};
    int unicode5[][2] = {0};
    assert(unicode5[0][1] == 0);
    int rows = sizeof(unicode5) / sizeof(unicode5[0]);
    int col = sizeof(unicode5[0]) / sizeof(int);
    int cell = sizeof(unicode5) / sizeof(int);


    //字符串一定是字符数组，字符数组不一定是字符串。字符串必须\0结尾。
    char b[] = {'a', 'b', 'c'};//字符数组
    char s[] = {'a', 'b', '\0'};//字符串
    char s2[] = {'a', 'b', 0};//字符串
    char s3[] = {'a', 'b', 0, 'b', 0};//多段字符串
    assert(sizeof(s3) == 5);

    //字符串，正常初始化
    char str[] = "23";//后面自动补充\0
    char a[10] = "abc";//最多写9个字符(超过报错error: initializer-string for char array is too long)，留一个\0
    assert(sizeof(a) == 10);
    assert(a[3] == '\0');
    int strl = strlen(a);
    assert(strl == 3);//不包括结束符号

    char src[100] = "hel\0lo";
    char dst[100];
    strcpy(dst, src);//直到拷贝\0
    strncpy(dst, src, 100);//src小于dst，且长度包括\0,会拷贝\0，但\0后的内容不能拷贝。
    // memcopy可以拷贝\0

    // ,;sscanf,sprintf

    // 比较 strncmp,strcmp
    // 查询 strchr,strstr
    // 追加 strcat,strncat
    // 切割 strtok
    char strt[] = "hello,word";
    char *mstrtest = strtok(strt, ",");//会破坏strt字符串，变为 "hello\0word",可以用strcpy备份
//    assert(mstrtest=="hello");

    int matoi = atoi("11");//字符串转整性；atof转浮点型

    //time
    // 随机数
//    int feed;
//    srand(feed)//feed一致，产生随机数也一致。
//    srand( time(NULL))//时间戳不一致
// sleep() linux秒单位，window 毫秒单位
    assert(1);
}

void __testPointer() {
    //1. 指针也是数据结构
    int *p;

    //2. 指针指向谁，谁的地址赋值给指针
    int a = 10;
    p = &a;
    //3. 直接操作指针变量，没有意义
//    p=123;
    //4. 操作*pointer，指针所指向的内存
    *p = 10;//相当于 a=10;

    //野指针，保存没有意义的地址；
    //conversion assigning to 'int *' from 'int' [-Wint-conversion]
//    p = 100;//没有意义，不知道地址的数据是什么；设置sysroot后报错

    //多级指针
    //[]方括号操作指针， *p相当于 p[0]，操作指针指向的内存
    assert(*p == p[0]);
    //万能指针 void*
    //1.不可以定义void类型的变量
    //2.void*可以指向任何类型的变量地址，使用指针时候，最好转化为目标地址的类型
    void *tvp;
    tvp = &a;
    *((int *) tvp) = 10;
    assert(a == 10);
    //const修饰指针变量。
    const int *tconstM = &a;//修饰*,所指向的内存数据不能修改
    int *const tconstP = &a;//地址不可修改
    const int *const tconst = &a;//指针和地址不可修改
    // 数组名 int a[];相当于 int *const a;地址不可修改。a=10；报错

    //字符串常量；放在data，文字常量区。只读，不能修改
    char *aaddr = "hello";
    char *baddr = "hello";
    assert(&*baddr==&*aaddr);

    char modifiable[] = "hello";

}

/**
 const
       +--------------+
pointer|  addr        +---------+   int *const pointer; 内存地址的addr
       +--------------+         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       |              |         |
       +--------------+         |
       |  data        +<--------+     const int *pointer；内存数据不能修改
       +--------------+
       |              |
       |              |
       |              |
       |              |
       |              |
       +--------------+

 */
 struct S_test{

 };
__test_struct(){
   int sizeOfS_test= sizeof(struct S_test);
   printf("%d",sizeOfS_test);
}
void testctypesuit() {
    __test_type_size();
    __test_malloc();
    __test_calloc();
    __testMacro();
    __testArray();
    __testPointer();
    __test_struct();
}
