package edu.ptu.androidtest.mockito;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.ptu.androidtest.R;

/**
 * Created by anshu.wang on 2016/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    Context mMockContext;

    @Test
    public void testMockioString() {
        //模拟Context.getString()方法，返回的值。然后再次执行时，mockitoValue就是根据mocket设置返回
        //Mock说白了就是打桩（Stub）或则模拟，当你调用一个不好在测试中创建的对象时，
        //Mock框架为你模拟一个和真实对象类似的替身来完成相应的行为
        Mockito.when(mMockContext.getString(R.string.app_name)).thenReturn("Test");
        String mockitoValue = mMockContext.getString(R.string.app_name);
        System.out.println(mockitoValue);
    }
}
