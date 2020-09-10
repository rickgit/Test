//
// Created by anshu-pc on 2020/7/24.
//

#include "base_type.h"
#include "string.h"

#ifdef  __cplusplus
extern "C" {//引用c需要设置 undefined reference to 'say_hello_c()'
//extern "C"，告诉g++编译器，不要对这些函数进行Name mangling，按照C编译器的方式去生成符号表符号 [objdump -t main 查看符号表](https://zhuanlan.zhihu.com/p/114669161)
#endif
#include "_60_media/_00_ffmpegTest.h"
#include "_10_ctest/hello.h"
#ifdef __cplusplus
}
#endif

char *base_type::test_typesize() {

//    GifFileType *pType = DGifOpenFileName("");
//    DGifSlurp(pType);//初始化
//
//    pType->UserData=

    char *string = say_hello_c();
    testFfmpeg();
    return string;

}