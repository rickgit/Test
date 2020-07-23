package edu.ptu.javatest._20_ooad._20_prince;

import org.junit.Test;

/**
 * 面向接口编程
 *
 *  依赖：接口方法，构造方法，set方法
 */
public class _02_DITest {
    @Test
    public void testDINoneTest(){
        new User().doWork(new HomeWork());

    }
    public class User{
        public void doWork(HomeWork homeWork){//方法依赖
            homeWork.doWork();
        }
    }
    public class HomeWork{
        public void doWork(){
            System.out.println("do HomeWork");
        }
    }

    @Test
    public void testDIMethodTest(){
        new UserDIMethod().doWork(new HomeWorkImpl());
        new UserDIMethod().doWork(new JobWork());

    }
    public class UserDIMethod{
        public void doWork(Work work){//方法依赖
            work.doWork();
        }
    }
    interface Work{
        public void doWork();
    }
    public class HomeWorkImpl implements Work{
        public void doWork(){
            System.out.println("do HomeWorkImpl");
        }
    }
    public class JobWork implements Work{
        public void doWork(){
            System.out.println("do JobWork");
        }
    }
}
