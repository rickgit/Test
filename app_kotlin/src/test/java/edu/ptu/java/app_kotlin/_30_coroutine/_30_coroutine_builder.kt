package edu.ptu.java.app_kotlin._30_coroutine

import kotlinx.coroutines.*
import org.junit.Test

class _30_coroutine_builder {
    @Test
    fun testThread(){
        runBlocking {
            launch { // 运行在父协程的上下文中，即 runBlocking 主协程
                println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
                println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(Dispatchers.Default) { // 将会获取默认调度器
                println("Default               : I'm working in thread ${Thread.currentThread().name}")
            }
            launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
                println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
            }
        }
    }

    //父协程总是等待所有的子协程执行结束
    @Test
    fun testSwitchThread(){
        runBlocking {
            newSingleThreadContext("Ctx1").use { ctx1 ->
                newSingleThreadContext("Ctx2").use { ctx2 ->
                    runBlocking(ctx1) {
                        println("Started in ctx1")
                        withContext(ctx2) {
                            println("Working in ctx2")
                        }
                        println("Back to ctx1")
                    }
                }
            }
        }
    }
}