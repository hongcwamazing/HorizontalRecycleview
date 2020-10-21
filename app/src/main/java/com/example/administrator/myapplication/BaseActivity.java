package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ARouter.getInstance().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //这样写会报错   ：
        // 这行代码的使用位置可能用错了。然后既然是在Application里面进行的初始化，
        // 那么就可以将这行释放资源的代码，写在Application生命周期的onTerminate( )里面，果不其然，
        // 项目运行后就没什么问题了。

        //ARouter.getInstance().destroy();
    }
}
