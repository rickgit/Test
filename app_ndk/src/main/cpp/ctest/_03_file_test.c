//
// Created by anshu-pc on 2020/7/26.
//

#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include <sys/epoll.h>
#include <string.h>
#include "_03_file_test.h"

typedef struct {
    char key[4];
    char value[4];

} shareperf;

void __test_mmap_write() {
    int fd;
    fd = open("./sharepref_mmap", O_CREAT | O_RDWR | O_TRUNC, 0077);
    write(fd, "hello", 5);

    shareperf *shareperfObj;
    shareperfObj = (shareperf *) mmap(NULL, sizeof(shareperf) * 10, PROT_READ | PROT_WRITE,
                                      MAP_SHARED, fd, 0);
    if (shareperfObj == (void *) -1) {
        //error
        return;
    }
    close(fd);
    for (int i = 0; i < 10; ++i) {
        memcpy((*(shareperfObj + i)).key, "key", 3);
        memcpy((*(shareperfObj + i)).value, "val", 3);
    }
    sleep(10);
    munmap(shareperfObj, sizeof(shareperf) * 10);
}

void __test_mmap_read() {
    int fd;
    fd = open("./sharepref_mmap", O_CREAT | O_RDWR, 0077);

    shareperf *shareperfObj;
    shareperfObj = (shareperf *) mmap(NULL, sizeof(shareperf) * 10, PROT_READ | PROT_WRITE,
                                      MAP_SHARED, fd, 0);
    if (shareperfObj == (void *) -1) {
        //error
        return;
    }
    close(fd);
    char *keyChar;
    char *valueChar;
    for (int i = 0; i < 10; ++i) {
        keyChar = (*(shareperfObj + i)).key;
        valueChar = (*(shareperfObj + i)).value;
    }
    sleep(10);
    munmap(shareperfObj, sizeof(shareperf) * 10);
}
//epoll 是在 Linux 2.6
//epoll_create(),epoll_ctl() , epoll_wait()
void __test_epoll(){
    int i = epoll_create(0);
}

void test_file_suit() {


}