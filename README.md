# test
test for simple life

##配置
1.修改测试文件夹的目录[ Android Studio如何修改单元测试的目录](http://blog.csdn.net/hjpdyxhjd/article/details/51891035)

android DSL jar包位置 **C:\Users\ {computer username}\ .gradle\caches\modules-2\files-2.1\com.android.tools.build\gradle\2.1.0\bcae59215a7b71367ece66de0bfaff6441102aea**
```
android {
    sourceSets {
        test.root 'src/tests/javaTest'
        test.java.srcDirs 'src/tests/javaTest/java'
        androidTest.root 'src/tests/androidTest'
        androidTest.java.srcDirs 'src/tests/androidTest/java'
    }
}
```


##junit text
1.junit库，mockito库

##espresso
依赖库
```
    androidTestCompile ('com.android.support.test.espresso:espresso-core:2.2.2'){
        exclude module: 'support-annotations'
    }


    defaultConfig {
    //需要加上，不然会报错
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }


    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class MainActivityTest {
        @Rule
        public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

        @Test
        public void testSample() {
            onView(withId(R.id.main_tv_name)).check(matches(isDisplayed()));
        }

    }
```
2. **Build Variants**需要选择debug，不然所写的类识别不到test库的类

 ### Espresso UI recorder
 
