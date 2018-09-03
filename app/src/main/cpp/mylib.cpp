#include <jni.h>
#include <sys/syscall.h>

#include <string>
#include <sys/types.h>
#include <unistd.h>
#include <android/log.h>
//#include <sys/_system_properties.h>
#include <sys/system_properties.h>

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
    env->MonitorEnter(instance);
    int *p = &i;//定义指针 指向这个地址

    printf("cvalue %d", i);
    *p = 150;
    int **px = &p;
    **px = 3330;
    LOGW("cvalue repleace  value %d %d %d", *p, i, **px);//取值 可是java那边 不会改变
    LOGW("%#x %#x %#x", *p, i, **px);//print address


    // 修改int指针指向的内存地址的值。

    env->MonitorExit(instance);

    return *p;


    /*
     * int *p ：一级指针，表示p所指向的地址里面存放的是一个int类型的值 
int **p ：二级指针，表示p所指向的地址里面存放的是一个指向int类型的指针（即p指向的地址里面存放的是一个指向int的一级指针） 
例如： 
int i=10; //定义了一个整型变量 
int *p=&i; //定义了一个指针指向这个变量 
int **p1=&p; //定义了一个二级指针指向p指针 
那么取出10的值方式为： 
printf(“i=[%d]\n”,*p); 
printf(“i=[%d]\n”,**p1);
     */


}

void exit_fn1(void) {
    printf("Exit function #1 called\n");
}

extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exist1(JNIEnv *env, jobject instance) {

//        _Exit(0);

}extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit2(JNIEnv *env, jobject instance) {
    LOGE("执行 exit start");

    _Exit(0);



    LOGE("执行 exit end");
}
extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit3(JNIEnv *env, jobject instance) {
    LOGE("执行 exit start");
    atexit(exit_fn1);//毫无效果
    LOGE("执行 exit end");
}

extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_exit1(JNIEnv *env, jobject instance) {

    LOGE("执行 exit start");
    abort();
    LOGE("执行 exit end");
}
extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_execute_1c(JNIEnv *env, jobject instance) {

    int value = strcmp("x", "y");
    return value;


}
extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_testThrowError(JNIEnv *env, jclass type) {


    env->FatalError("test_throw_error");

}
extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_getJVMVersion(JNIEnv *env, jclass type) {

    return env->GetVersion();


}
extern "C" JNIEXPORT jboolean JNICALL
Java_cn_qssq666_testjni_MainActivity_checkFileExist(JNIEnv *env, jobject instance, jstring path_) {
    jboolean value = JNI_TRUE;
    const char *path = env->GetStringUTFChars(path_, &value);
    LOGW("PATH %s", path);
    FILE *fp = fopen(path, "r");

    bool exist = fp != NULL;
    if (exist) {
        fclose(fp);
    }


//    env->
    env->ReleaseStringUTFChars(path_, path);

    return exist;
}extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_moni_1exit(JNIEnv *env, jobject instance) {

    env->MonitorEnter(instance);
    env->MonitorExit(instance);


}extern "C" JNIEXPORT void JNICALL
Java_cn_qssq666_testjni_MainActivity_showToast(JNIEnv *env, jobject instance) {


    //currentApplication
    jclass jclassActivityThead = env->FindClass("android/app/ActivityThread");
    if (env->ExceptionCheck()) {
        LOGE("没有找到");
        env->ExceptionClear();
        return;

    }

    //currentApplication () {

    jmethodID currentApplicationId = env->GetStaticMethodID(jclassActivityThead,
            "currentApplication", "()Landroid/app/Application;");
    if (env->ExceptionCheck()) {
        LOGE("没有找到application method");
        env->ExceptionClear();
        return;

    }

    jobject jobjectApplication = env->CallStaticObjectMethod(jclassActivityThead,
            currentApplicationId);
    if (env->ExceptionCheck()) {
        LOGE("没有找到application");
        env->ExceptionClear();
        return;

    }

    jclass toastClass = env->FindClass("android/widget/Toast");
    if (env->ExceptionCheck()) {
        LOGE("没有找到Toast");
        env->ExceptionClear();
        return;

    }


    jmethodID makeTextMethodId = env->GetStaticMethodID(toastClass, "makeText",
            "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;");

    if (env->ExceptionCheck()) {
        LOGE("没有找到Toast Method ");
        env->ExceptionClear();
        return;

    }

    jstring str = env->NewStringUTF("你被我拦截!");

    jint duration = 0;
    jobject toastObj = env->CallStaticObjectMethod(toastClass, makeTextMethodId, jobjectApplication,
            str, duration);

    if (env->ExceptionCheck()) {
        LOGE("没有找到Toast Method ");
        env->ExceptionClear();
        return;

    }

    jmethodID toastShowJmi = env->GetMethodID(toastClass, "show", "()V");
    if (env->ExceptionCheck()) {
        LOGE("没有找到Toast show methodid ");
        env->ExceptionClear();
        return;

    }

    if (jobjectApplication == NULL) {
        LOGE("没有application");
    }
    env->CallVoidMethod(toastObj, toastShowJmi);




    //  public static Toast makeText(Context context, CharSequence text, @Duration int duration) {






    LOGW("application address:%p", *jobjectApplication);


//https://blog.csdn.net/yuzhou_zang/article/details/78410632

}
extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_getApplicationPackage(JNIEnv *env, jobject instance) {



    //currentApplication
    jclass jclassActivityThead = env->FindClass("android/app/ActivityThread");
    if (env->ExceptionCheck()) {
        LOGE("没有找到");
        env->ExceptionClear();
        return env->NewStringUTF("没有找到  ActivityThread");

    }

    //currentApplication () {

    jmethodID currentApplicationId = env->GetStaticMethodID(jclassActivityThead,
            "currentPackageName", "()Ljava/lang/String;");
    if (env->ExceptionCheck()) {
        LOGE("没有找到 currentPackageName method");
        env->ExceptionClear();
        return env->NewStringUTF("没有找到 currentPackageName method");

    }

    jstring packageName = static_cast<jstring>(env->CallStaticObjectMethod(jclassActivityThead,
            currentApplicationId));
    if (env->ExceptionCheck()) {
        LOGE("call fail");
        env->ExceptionClear();
        return env->NewStringUTF("currentPackageName call fail");

    }



    return packageName;
}extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_readConfig(JNIEnv *env, jobject instance, jstring key_) {
    const char *key = env->GetStringUTFChars(key_, 0);


    char buf[PROP_VALUE_MAX];
//    __system_property_get("net.dns1", buf);
//    __system_property_get("net.dns2", buf);
    __system_property_get(key, buf);
//    __system_property_get("ro.secure", buf);
//    __system_property_get("ro.debuggable", buf);

    LOGW("read value:%s", buf);


    env->ReleaseStringUTFChars(key_, key);

    /*
     *
     * https://blog.csdn.net/pathfinder163/article/details/8807405
     * 中加载默认属性：
/ default.prop
/system/build.prop
/system/default.prop
/data/local.prop
     */
    return env->NewStringUTF(buf);
}extern "C" JNIEXPORT jint JNICALL
Java_cn_qssq666_testjni_MainActivity_writeConfig(JNIEnv *env, jobject instance, jstring key_,
                                                 jstring value_) {
    const char *key = env->GetStringUTFChars(key_, 0);
    const char *value = env->GetStringUTFChars(value_, 0);

    char buf[PROP_VALUE_MAX];
    int result = __system_property_set(key, value);

    if (env->ExceptionCheck()) {
        jclass excepClass = env->FindClass("java/lang/Exception");
        env->ExceptionClear();
        env->ThrowNew(excepClass, "设置失败!");

    }

    env->ReleaseStringUTFChars(key_, key);
    env->ReleaseStringUTFChars(value_, value);
    return result;
}extern "C" JNIEXPORT jstring JNICALL
Java_cn_qssq666_testjni_MainActivity_printStr(JNIEnv *env, jobject instance) {
    /*
     * strncmp
     * str1 -- 要进行比较的第一个字符串。
str2 -- 要进行比较的第二个字符串。
n -- 要比较的最大字符数。
     */
    pid_t pid = syscall(__NR_getpid);
//    int pid=getpid();
    char line[128];
    char filename[128];
    sprintf(filename, "/proc/%d/status", pid);
    LOGW("READ FILE %s", filename);
    FILE *fd = fopen(filename, "r");
    if (fd == NULL) {

        char returnStr[128];
        sprintf(returnStr, "cannot read file %s", filename);
        return env->NewStringUTF(returnStr);
    }
    while (fgets(line, 128, fd)) {
        if (strncmp(line, "TracerPid", 9) == 0) {//found
            int status = atoi(&line[10]);
            LOGI("########## found TracerPid status = %d, %s", status, line);
            fclose(fd);
            //pid_t pid = syscall(__NR_getpid);
            syscall(__NR_close, fd);
            if (status != 0) {
                LOGI("########## FBI WARNING ##########");
                LOGI("######### FIND DEBUGGER #########");
                kill(pid, SIGKILL);
//                syscall(__NR_kill, pid); //可以杀死系统
//                syscall(CLD_KILLED);
//                syscall(__NR_kill, SYS_kill);
//                syscall(__NR_tkill);
            }
            break;
        }

    }
   /* {
        tprintf("%ld, %s",
                widen_to_long(tcp->u_arg[0]),
                signame(tcp->u_arg[1]));

    }*/
//    tgkill(pid,-1,0);
//    syscall(__NR_tgkill);
//    syscall(__NR_kill);
//    syscall(__NR_kill, pid); //可以杀死系统

//    cat /proc/pid/status

    return env->NewStringUTF("HELLO WORLD");
}