#include <jni.h>
#include <string>
#include "pip_test.h"
#include "base_type.h"
#include "jnitest/jnitest.h"
#include<android/log.h>

//JNIEXPORT 该声明的作用是保证在本动态库中声明的方法 , 能够在其他项目中可以被调用 ;
//Linux 宏转化后 extern "C" __attribute__ ((visibility ("default"))) jstring
//windows 宏转化后 extern "C" __declspec(dllexport) jstring __stdcall
extern "C" JNIEXPORT jstring JNICALL
Java_edu_ptu_java_myapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    pip_test* p;
    int i = p->getAge();
    __android_log_print(ANDROID_LOG_DEBUG,"TAG","类的属性 age %d",i);
    base_type bt;
    __android_log_print(ANDROID_LOG_DEBUG,"TAG","类的属性 age %s",bt.test_typesize());

    testJniSuit(env);
    return env->NewStringUTF(hello.c_str());
}
