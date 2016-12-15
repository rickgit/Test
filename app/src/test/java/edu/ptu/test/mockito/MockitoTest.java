package edu.ptu.test.mockito;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.ptu.test.R;

/**
 * Created by anshu.wang on 2016/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    Context mMockContext;

    @Test
    public void testMockioString(){
        //模拟Context.getString()方法，返回的值。然后再次执行时，mockitoValue就是根据mocket设置返回
        Mockito.when(mMockContext.getString(R.string.app_name)).thenReturn("Test");
        String mockitoValue = mMockContext.getString(R.string.app_name);
        System.out.println(mockitoValue);
    }
}
