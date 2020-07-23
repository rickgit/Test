package edu.ptu.javatest._20_ooad._20_prince;

import org.junit.Test;

public class _00_SingleResponseTest {
    @Test
    public void testSingleResponseTest(){
        new Tools().doWork("家庭作业");
        new Tools().doWork("工作任务");

        new ToolsSplitMethod().doHomeWork("家庭作业");
        new ToolsSplitMethod().doJobWork("工作任务");

        new HomeToolsSplitToClass().doWork("家庭作业");
        new JobToolsSplitToClass().doWork("工作任务");
    }
    public static class Tools{
        public void doWork(String tool){
            System.out.printf(tool);
        }
    }

    //方法少，可以用这种。否则用类拆分
    public static class ToolsSplitMethod{
        public void doWork(String tool){
            System.out.printf(tool);
        }
        public void doHomeWork(String tool){
            System.out.printf(tool);
        }
        public void doJobWork(String tool){
            System.out.printf(tool);
        }
    }

    public static class JobToolsSplitToClass{
        public void doWork(String tool){
            System.out.printf(tool);
        }

    }
    public static class HomeToolsSplitToClass{
        public void doWork(String tool){
            System.out.printf(tool);
        }

    }
}
