package edu.ptu.java.app_kotlin._30_coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import org.junit.Test

class _38_coroutine_select {
    fun CoroutineScope.fizz() = produce<String> {
        while (true) { // 每 300 毫秒发送一个 "Fizz"
            delay(300)
            send("Fizz")
        }
    }

    fun CoroutineScope.buzz() = produce<String> {
        while (true) { // 每 500 毫秒发送一个"Buzz!"
            delay(500)
            send("Buzz!")
        }
    }
   suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
        select<Unit> { // <Unit> 意味着该 select 表达式不返回任何结果
            fizz.onReceive { value ->  // 这是第一个 select 子句
                println("fizz -> '$value'")
            }
            buzz.onReceive { value ->  // 这是第二个 select 子句
                println("buzz -> '$value'")
            }
        }
    }
    @Test
    fun testSelect(){
        runBlocking {
            val fizz = fizz()
            val buzz = buzz()
            repeat(7) {
                selectFizzBuzz(fizz, buzz)
            }
            coroutineContext.cancelChildren()
        }
    }
}