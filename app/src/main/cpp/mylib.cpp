#include <jni.h>
#include <string>

#include <android/log.h>
#define LOG_TAG "TEST_JNI"

#define DEBUG
#define ANDROID_PLATFORM

#ifdef DEBUG
#ifdef ANDROID_PLATFORM
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))
#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__))
#define LOGW(...) ((void)__android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__))
#define LOGE(...) ((void)__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__))
#else  //如果 不是 android平台 直接替换 。
#define LOGD(fmt, ...) printf(fmt"\n", ##__VA_ARGS__)
		#define LOGI(fmt, ...) printf(fmt"\n", ##__VA_ARGS__)
		#define LOGW(fmt, ...) printf(fmt"\n", ##__VA_ARGS__)
		#define LOGE(fmt, ...) printf(fmt"\n", ##__VA_ARGS__)
#endif
#else
#define LOGD(...)
	#define LOGI(...)
	#define LOGW(...)
	#define LOGE(...)

#endif



extern "C" JNIEXPORT jstring

JNICALL Java_cn_qssq666_testjni_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}



extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_getMessage(JNIEnv *env, jobject) {




    int reuslt = strlen("555");
    if (reuslt > 10) {
        exit(0);
    }

    return env->NewStringUTF("message0");
}

extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_getMessage1(JNIEnv *env, jobject) {



    int reuslt = strlen("555");
    if (reuslt > 10) {
        exit(0);
    }



    return env->NewStringUTF("message1");
}


extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_calcAdd(JNIEnv *env, jobject, jint value, jint value1) {



    int reuslt = strlen("555");
    if (reuslt > 10) {
        exit(0);
    }

    return value + value1;

}
extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_calcAddStr(JNIEnv *env, jobject, jstring value_,
                                                jstring value1_) {
    char *value = const_cast<char *>(env->GetStringUTFChars(value_, 0));
    const char *value1 = env->GetStringUTFChars(value1_, 0);

/*
    env->ReleaseStringUTFChars( value_, value);
    env->ReleaseStringUTFChars( value1_, value1);
*/

    return env->NewStringUTF("pingjie nimma");
//    const char *valueresult=strcat("xxxx","fff");
//    return env->NewString(strcat("s","33"),3 );
}
extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_exit(JNIEnv *env, jobject instance) {


    exit(0);
    return 0;

}

extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_testCRepleaceValue(JNIEnv *env, jobject instance, jint i) {

    // int 类型的指针 *p指针指向int变量i的内存地址。
    int *p = &i;//取地址

    printf("cvalue %d", i);
    *p = 150;
    printf("cvalue repleace address value %d", i);
    // 修改int指针指向的内存地址的值。

    return *p;



}

void exit_fn1(void)
{
    printf("Exit function #1 called\n");
}
extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exist1(JNIEnv *env, jobject instance) {

//        _Exit(0);

}extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit2(JNIEnv *env, jobject instance) {


    _Exit(0);

}
extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit3(JNIEnv *env, jobject instance) {

    atexit(exit_fn1);
}

extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit1(JNIEnv *env, jobject instance) {

    LOGE("执行 exit start");
    abort();
    LOGE("执行 exit end");
}