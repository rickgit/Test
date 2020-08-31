package edu.ptu.java.app_kotlin._30_coroutine

import kotlinx.coroutines.delay
import org.junit.Test
import kotlinx.coroutines.*
import org.junit.Assert
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

/**
 * 阻塞	              挂起
Sychnroized/Lock	 Mutex
BlockQueue	         Channel
BIO	                 NIO
sleep	             delay
线程安全的容器	     暂时无
 */

//顺序编程的方式实现异步以及并发任务，这比响应式流的链式调用更加直观
//协程：挂起和内部唤醒
//协程构建器: launch 和 async(并发),invoke,withContext(改变协程的上下文)，（kotlinx-coroutines-core-1.3.0-sources.jar!/commonMain/Builders.common.kt）
//           runBlocking封装GlobalScope                     (  kotlinx-coroutines-core-1.3.0-sources.jar!/jvmMain/Builders.kt:36)
 //协程范围和调度器 CoroutineStart(before thread)/CoroutineDispatcher(resume后的调度)
//挂起函数（ CPS 变换后的函数多了一个 Continuation<T> 类型的参数） fun <R, T> (suspend (R) -> T).startCoroutineCancellable(receiver: R, completion: Continuation<T>) = runSafely(completion)
// GlobalScope 中启动时，使用的是由 Dispatchers.Default 代表的默认调度器
class _30_coroutine {


    @Test
    fun testGlobalScopeLaunch() {
//        CoroutineScope.launch(
//                context:  EmptyCoroutineContext,
//        start:  CoroutineStart.DEFAULT,
//        block:{}
//        ): Job
        val mJob = GlobalScope.launch {
            pringCoroutineScheduler()
            // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }//Job
        val value: Any? = GlobalScope.coroutineContext[Job];
//        mJob.cancel()
        GlobalScope.cancel(CancellationException("ms"))
        //非协程等同方式
        thread {
            Thread.sleep(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        Thread.sleep(3000)

        //超时
        runBlocking {
            println()
            withTimeout(1300L) {
                println()
            }
        }
    }

    @Test
    fun testGlobalScopeAsync() {
        GlobalScope.async {
            pringCoroutineScheduler()
            // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }//Deferred


        Thread.sleep(3000)
    }

    private fun pringCoroutineScheduler() {
        val declaredField = Thread.currentThread().javaClass.getDeclaredField("this\$0")
        declaredField.isAccessible = true
        var data = declaredField.get(Thread.currentThread())
        println(data.toString())
    }

    @Test
    fun testrunBlocking() {
        runBlocking {
            var counter:Int=0
            pringCoroutineScheduler()
            println("Counter = $counter")
        }
    }
    @Test
    fun testWithContext() {
            runBlocking {
                var counter:Int=0
                pringCoroutineScheduler()
                println("Counter = $counter")
            }
        Thread.sleep(3000)

    }
    /////// 官方例子
    fun corountineCancel() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
    }

    fun coroutineNoCancellable() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并等待它结束
        println("main: Now I can quit.")
    }

    fun coroutineTimeOut() = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }

    fun coroutineTimeOutOrNull() = runBlocking {
        withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            Assert.fail()
        }
    }

    @Test
    fun testCoroutinesCancelOrTimeout() {
//        corountineCancel()
//        coroutineNoCancellable()
//        try {
//            coroutineTimeOut()
//        } catch (  e:Exception) {
//            Assert.assertTrue(e is TimeoutCancellationException)
//        }
        val coroutineTimeOutOrNull = coroutineTimeOutOrNull()
        println("result $coroutineTimeOutOrNull")

    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // 假设我们在这里做了一些有用的事
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }

    fun count() = runBlocking {
        val measureTimeMillis = measureTimeMillis {
            var a = doSomethingUsefulOne()
            var b = doSomethingUsefulTwo()
            print("count ${a + b}")
        }
        println("count 耗时 $measureTimeMillis")
    }

    fun countLazy() = runBlocking {
        val measureTimeMillis = measureTimeMillis {
            var a = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }//CoroutineStart.LAZY 防止和kotlin标准lazy冲突
            var b = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            print("count ${a.await() + b.await()}")//需要调用await才会阻塞获取结果
        }
        println("countLazy 耗时 $measureTimeMillis")
    }

    @Test
    fun testCoroutine() {
        count()//2s72'
        countLazy()//2s72'

    }

    @Test
    fun testStandardCoroutine() {
        suspend fun concurrentSum(): Int = coroutineScope {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            one.await() + two.await()
        }
        runBlocking<Unit> {
            try {
                println("${concurrentSum()}")
            } catch (e: ArithmeticException) {
                println("Computation failed with ArithmeticException")
            }
        }

    }

    //-Dkotlinx.coroutines.debug
}