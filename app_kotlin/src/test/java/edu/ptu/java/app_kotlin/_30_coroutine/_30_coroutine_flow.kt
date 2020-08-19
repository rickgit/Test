package edu.ptu.java.app_kotlin._30_coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.Test

class _30_coroutine_flow {
    @Test
     fun testSequeue(){
        listOf(1, 2, 3).forEach{
            println(it)
        }
        //sequence线程处理
        sequence { // 序列构建器
            for (i in 1..3) {
                Thread.sleep(1000) // 假装我们正在计算
                yield(i) // 产生下一个值
            }
        }.forEach {
            println(it)
        }
        //协程处理流
        runBlocking {
            flow { // 流构建器
                for (i in 1..3) {
                    delay(1000) // 假装我们在这里做了一些有用的事情
                    emit(i) // 发送下一个值
                }
            }.collect { value -> println(value) }
        }

    }
}