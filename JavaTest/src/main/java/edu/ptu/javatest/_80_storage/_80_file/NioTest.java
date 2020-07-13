package edu.ptu.javatest._80_storage._80_file;
//jdk1.4 nonblock-io 不等待内核拷贝磁盘数据，拷贝应用空间阻塞。解决并发量大
//多路复用 channel 两个操作都阻塞。相对于bio，不用分配单独线程读写，selector获取信息，通过buffer（nio）获取channel数据
//jdk7 nio2
public class NioTest {
}
