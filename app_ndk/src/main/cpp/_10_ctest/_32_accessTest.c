//
// Created by anshu-pc on 2020/8/3.
//
#include "_30_file_test.h"
#include <unistd.h>
#include <assert.h>
#define PGHDR_TO_DATA(P)  ((void*)(&(P)[1]))

void testAccess(){
    //检查调用进程是否可以对指定的文件执行某种操作。
//    R_OK      测试读许可权
//    W_OK      测试写许可权
//    X_OK      测试执行许可权
//    F_OK      测试文件是否存在
    int res = access("/data/data/edu.ptu.java.myapplication/lib/libnative-lib.so", F_OK);
//    成功执行时，返回0。失败返回-1，errno被设为以下的某个值
//    EINVAL： 模式值无效
//    EACCES： 文件或路径名中包含的目录不可访问
//    ELOOP ： 解释路径名过程中存在太多的符号连接
//    ENAMETOOLONG：路径名太长
//    ENOENT：路径名中的目录不存在或是无效的符号连接
//    ENOTDIR： 路径名中当作目录的组件并非目录
//    EROFS： 文件系统只读
//    EFAULT： 路径名指向可访问的空间外
//    EIO：输入输出错误
//    ENOMEM： 不能获取足够的内核内存
//    ETXTBSY：对程序写入出错
    assert(res==0);


}

