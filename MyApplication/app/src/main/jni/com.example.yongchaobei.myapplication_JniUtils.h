//
// Created by yongchao.bei on 2017/4/11.
//

#include <com.example.yongchaobei.myapplication_JniUtils.h>
JNIEXPORT jstring JNICALL Java_com.example.yongchaobei.myapplication_JniUtils_sayHello
  (JNIEnv *env, jclass jobj) {
    return (*env)->NewStringUTF(env,"Hello JNI!");
}
