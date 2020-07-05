package edu.ptu.javatest._20_ooad;

import org.junit.Test;

//（提供方）拓展开放；（使用方）修改关闭
public class _04_ocpTest {
    @Test
    public void testOcpNone() {
        new UserModify().invokeProvide(new ProvideBook());
        new UserModify().invokeProvide(new ProvidePen());
        //后续拓展需要修改使用方UserModify
    }

    public static interface Provide {

    }

    public static class ProvideBook implements Provide {

    }

    public static class ProvidePen  implements Provide{

    }

    public static class UserModify {
        public void invokeProvide(Provide provide) {
            if (provide instanceof ProvideBook)
                System.out.println("use book");
            else if (provide instanceof ProvidePen)
                System.out.println("use Pen");
            else System.out.println("use other");
        }
    }
    @Test
    public void testOcp () {
        new UserModifyClose().invokeProvide(new ProvideExtends() {
            @Override
            public void methodToExtend() {
                System.out.println("use book");
            }
        });
        new UserModifyClose().invokeProvide(new ProvideExtends() {
            @Override
            public void methodToExtend() {
                System.out.println("use Pen");
            }
        });
        //后续拓展需要修改使用方UserModify
    }
    public static interface ProvideExtends {
        public void methodToExtend();
    }
    public static class UserModifyClose {
        public void invokeProvide(ProvideExtends provide) {
             provide.methodToExtend();
        }
    }
}
