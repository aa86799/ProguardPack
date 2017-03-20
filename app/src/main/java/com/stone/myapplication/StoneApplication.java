package com.stone.myapplication;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 20/03/2017 14 14
 */
public class StoneApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
