//
// Created by anshu-pc on 2020/7/25.
//

#include <assert.h>
#include <unistd.h>
#include "_50_thread_test.h"

void *run(void *arg) {
    pthread_detach(pthread_self());// 不阻塞 父线程

    char *thread_tag = (char *) arg;
    for (int i = 0; i < 5; ++i) {

    }
    pthread_exit(thread_tag);
}

void __testThreadRun() {
    char *tag = "n0.1";
    pthread_t tid;
    pthread_create(&tid, NULL, run, tag);
    void *return_val;
    pthread_join(tid, &return_val);//阻塞当前线程；
    assert(tag == return_val);
//    pthread_detach(tid);// 不会阻塞 当前线程
    usleep(200 * 200);
    usleep(200 * 200);
}

// 互斥锁
pthread_mutex_t mutex;

//pthread_mutex_init     pthread_mutex_lock pthread_mutex_timedlock  pthread_mutex_trylock
// pthread_mutex_destroy pthread_mutex_unlock
void *async_Run(void *arg) {
    // lock
    pthread_mutex_lock(&mutex);
    char *thread_tag = (char *) arg;
    for (int i = 0; i < 100; ++i) {

    }
    // unlock
    pthread_mutex_unlock(&mutex);
    return thread_tag;
}

void __testThreadSyncLock() {
    pthread_mutex_init(&mutex, NULL);
    pthread_t t1, t2;

    pthread_create(&t1, NULL, async_Run, "No.1");
    pthread_create(&t2, NULL, async_Run, "No.2");

    void *ret_val[2];
    pthread_join(t1, &ret_val[0]);
    pthread_join(t2, &ret_val[1]);

    assert("No.1" == (char *) ret_val[0]);
    assert("No.2" == (char *) ret_val[1]);

    pthread_mutex_destroy(&mutex);
}

volatile int shared_count = 0;
pthread_mutex_t pthreadMutexLock;//锁
pthread_cond_t pthreadCond;//条件变量
void *producer_run(void *arg) {
    int producer_times = 0;
    char *tag = (char *) arg;
    for (;;) {
        pthread_mutex_lock(&pthreadMutexLock);

        shared_count++;
        pthread_cond_signal(&pthreadCond);
        pthread_mutex_unlock(&pthreadMutexLock);
        usleep(200 * 200);

        if (producer_times++ >= 10)
            break;
    }
//    return producer_times;
}

void *consumer_run(void *arg) {
    char *tag = (char *) arg;

    int consumer_times = 0;
    for (;;) {
        pthread_mutex_lock(&pthreadMutexLock);
        while (shared_count == 0) {
            pthread_cond_wait(&pthreadCond, &pthreadMutexLock);
        }
        shared_count--;
        pthread_mutex_unlock(&pthreadMutexLock);
        usleep(500 * 1000);

        if (consumer_times++ >= 10)
            break;
    }
//    return consumer_times;
}

void __test_thread_communicate() {
    pthread_mutex_init(&pthreadMutexLock, NULL);
    pthread_cond_init(&pthreadCond, NULL);

    pthread_t producer_id, consumer_id;

//    pthread_create(&producer_id, NULL, producer_run, "producer");
//    pthread_create(&consumer_id, NULL, consumer_run, "consumer");

//    pthread_join(producer_id, NULL);
//    pthread_join(consumer_id, NULL);

    pthread_mutex_destroy(&pthreadMutexLock);
    pthread_cond_destroy(&pthreadCond);
}

void testThreadSuit() {
    __testThreadRun();
    __testThreadSyncLock();
    __test_thread_communicate();
}
