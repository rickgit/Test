//
// Created by anshu-pc on 2020/7/29.
//

#include "microsystest.h"
void testSyscall(){
    __asm__(
            "ldr r7,=6"//include\asm-arm\unistd.h
            "swi"//arch\arm\kernel\calls.S
            //
            );
}
//物理地址直接映射，static struct map_desc s3c_iodesc[]
void testAddressMap(){

}
//字符设备以字节为读写；块设备 512k；网络设备。。。
//https://www.bilibili.com/video/BV12E411S7ab?p=16
void testDeviceDriver(){
    //mknode /dev/文件名 c 主设备号 次设备号
    //cat /proc/devices
}

//查找write方法所需头文件，man 2 write
//arm-linux-readelf -d bin；查找bin执行文件依赖的库


void testMiscCharDevicerDriver(){
    //binder 驱动
}