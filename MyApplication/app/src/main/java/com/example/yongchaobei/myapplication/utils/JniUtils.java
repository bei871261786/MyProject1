package com.example.yongchaobei.myapplication.utils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/4/11 14:07
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class JniUtils {
    //加载so库
    static {
        System.loadLibrary("hello_jni");
    }

    //native方法
    public static native String helloJni();

}
