package edu.ptu.java.app_kotlin._30_coroutine

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class _34_coroutine_channel {
    @Test
    fun  check(){
        runBlocking {
            val channel = Channel<Int>()
            launch {
                for (x in 1..5) channel.send(x * x)
                channel.close() // 我们结束发送
            }
// 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
            for (y in channel) println(y)
            println("Done!")
        }
    }
}