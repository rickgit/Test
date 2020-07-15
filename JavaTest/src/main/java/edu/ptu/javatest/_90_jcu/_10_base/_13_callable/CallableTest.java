package edu.ptu.javatest._90_jcu._10_base._13_callable;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    @Test
    public void testCallableThread(){
        ThreadCallable threadCallable = new ThreadCallable();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(threadCallable);
        new Thread(integerFutureTask).start();
        try {
            System.out.println(integerFutureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static class ThreadCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int temp = 0;
            for (int i = 0; i < 100; i++) {
                temp++;
            }
            return temp;
        }
    }
}
