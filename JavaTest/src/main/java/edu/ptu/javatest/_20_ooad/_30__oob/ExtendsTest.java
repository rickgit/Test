package edu.ptu.javatest._20_ooad._30__oob;

public class ExtendsTest {
    public  static class P{
        public final int getX(){
            return 0;
        }
    }
    public  static class C extends P{
        public final int getX(int a){//final修饰可以重载
            return 0;
        }
    }
}
