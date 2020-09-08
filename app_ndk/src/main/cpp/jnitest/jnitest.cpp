//
// Created by anshu-pc on 2020/7/25.
//

#include <assert.h>
#include <malloc.h>
#include <string.h>
#include <string>
#include "jnitest.h"
//方法注册


//typedef uint8_t  jboolean; /* unsigned 8 bits */
//typedef int8_t   jbyte;    /* signed 8 bits */
//typedef uint16_t jchar;    /* unsigned 16 bits */
//typedef int16_t  jshort;   /* signed 16 bits */
//typedef int32_t  jint;     /* signed 32 bits */
//typedef int64_t  jlong;    /* signed 64 bits */
//typedef float    jfloat;   /* 32-bit IEEE 754 */
//typedef double   jdouble;  /* 64-bit IEEE 754 */
void testJniType() {
    assert(sizeof(jbyte) == 1);
    assert(sizeof(jboolean) == 1);
    assert(sizeof(jchar) == 2);
    assert(sizeof(jshort) == 2);
    assert(sizeof(jint) == 4);
    assert(sizeof(jlong) == 8);
    assert(sizeof(jfloat) == 4);
    assert(sizeof(jdouble) == 8);
    assert(sizeof(jobject) == 4);


    assert(sizeof(jarray) == 4);


}

//typedef _jobject*       jobject;
//typedef _jclass*        jclass;
//typedef _jstring*       jstring;
//typedef _jarray*        jarray;
//typedef _jobjectArray*  jobjectArray;
//typedef _jbooleanArray* jbooleanArray;
//typedef _jbyteArray*    jbyteArray;
//typedef _jcharArray*    jcharArray;
//typedef _jshortArray*   jshortArray;
//typedef _jintArray*     jintArray;
//typedef _jlongArray*    jlongArray;
//typedef _jfloatArray*   jfloatArray;
//typedef _jdoubleArray*  jdoubleArray;
//typedef _jthrowable*    jthrowable;
//typedef _jobject*       jweak;
void testJniObjType() {

}
std::string jstring2str(JNIEnv* env, jstring jstr)
{
    char*   rtn   =   NULL;
    jclass   clsstring   =   env->FindClass("java/lang/String");
    jstring   strencode   =   env->NewStringUTF("UTF-8");
    jmethodID   mid   =   env->GetMethodID(clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
    jbyteArray   barr=   (jbyteArray)env->CallObjectMethod(jstr,mid,strencode);
    jsize   alen   =   env->GetArrayLength(barr);
    jbyte*   ba   =   env->GetByteArrayElements(barr,JNI_FALSE);
    if(alen   >   0)
    {
        rtn   =   (char*)malloc(alen+1);
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    env->ReleaseByteArrayElements(barr,ba,0);
    std::string stemp(rtn);
    free(rtn);
    return   stemp;
}
//字符签名
//typedef union jvalue {
//    jboolean    z;
//    jbyte       b;
//    jchar       c;
//    jshort      s;
//    jint        i;
//    jlong       j;
//    jfloat      f;
//    jdouble     d;
//    jobject     l;
//} jvalue;
//数组 [z,[b.......
void testJniJvalue(JNIEnv *env) {
//    jvalue.z;
//    jclass jclazz = (env)->FindClass(env, "com/mao/ccalljava/JNI");
    jclass str_cls = (env)->FindClass("java/lang/String");
    //创建默认对象
    jobject pJobject = env->AllocObject(str_cls);
    jmethodID pId = env->GetMethodID(str_cls, "toString", "()Ljava/lang/String;");
    jobject methodret = env->CallObjectMethod(pJobject, pId);
    const char *fromjava = env->GetStringUTFChars(static_cast<jstring>(methodret), 0);

    assert(methodret!=NULL);

    //通过构造方法
    jmethodID constructor_mid = (env)->GetMethodID( str_cls, "<init>", "([BLjava/lang/String;)V");
    jbyteArray  arg = env->NewByteArray(4);
     env->SetByteArrayRegion(arg, 0, 4, reinterpret_cast<const jbyte *>("hello "));
    jstring charsetName = (env)->NewStringUTF( "GB2312");
    (env)->NewObject(str_cls,constructor_mid,arg,charsetName);

    jobject methodret2 = env->CallObjectMethod(pJobject, pId);
    const char *fromjava2 = env->GetStringUTFChars(static_cast<jstring>(methodret), 0);
    assert(methodret2!=NULL);
}

void testJniSuit(JNIEnv *jniEnv) {
    testJniType();
    testJniJvalue(jniEnv);
    jclass pJclass = (jniEnv)->FindClass("android/app/NativeActivity");
    assert(pJclass != 0);

}


_jstring * get_dynamic_string(JNIEnv *env, jobject thiz){
    std::string hello = "string from dynamic";

    return env->NewStringUTF(hello.c_str());
}
static JNINativeMethod getMethods[] = {
        {"stringFromDynamicMethod", "()Ljava/lang/String;",(void*)get_dynamic_string},
};

// 静态注册：javah 生成c文件
//动态注册方法
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    //获取JNIEnv
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    if(env->RegisterNatives(env->FindClass("edu/ptu/java/myapplication/MainActivity")
            ,getMethods,sizeof(getMethods)/ sizeof(getMethods[0])) < 0){
        return JNI_FALSE;
    }
    return JNI_VERSION_1_6;
}