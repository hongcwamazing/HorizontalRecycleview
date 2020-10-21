package com.example.administrator.myapplication;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    private boolean isDebug = true;
    //注：请不要执行耗时操作，否则会拖慢应用程序启动速度
    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(MyApplication.this); // 尽可能早，推荐在Application中初始化
    }

    //系统在内存不足时会按照LRU Cache中从低到高杀死进程；优先杀死占用内存较高的应用
    //若应用占用内存较小 = 被杀死几率降低，从而快速启动（即热启动 = 启动速度快）
    //可回收的资源包括：
    //a. 缓存，如文件缓存，图片缓存
    //b. 动态生成 & 添加的View


    //特别注意：OnTrimMemory（） &  OnLowMemory（） 关系
    //OnTrimMemory（）是 OnLowMemory（） Android 4.0后的替代 API
    //OnLowMemory（） =  OnTrimMemory（）中的TRIM_MEMORY_COMPLETE级别
    //若想兼容Android 4.0前，请使用OnLowMemory（）；否则直接使用OnTrimMemory（）即可


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
