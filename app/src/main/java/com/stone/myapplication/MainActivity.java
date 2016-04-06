package com.stone.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stone.myapplication.exception.MyException;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {

    public  String HOBBY = "足球";
    public  int LEVEL = 8;
    public  int STATE = 9;
    String[ ] axxxa;
    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = new User();
        user.setName("小格");

        setTitle("测试EventBus消息");

        EventBus.getDefault().register(this);//注册成为订阅者

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
        System.out.println("发送方> " + Thread.currentThread().getName());
        EventBus.getDefault().post(MainActivity.this); //可post任意类型，只需要相关的onEvent(这里的参数类型与post时一致)


        Log.i("aa", "bb");
        say();
        Log.i("aa", "bb");
        new MyException("","kk");
        Tim t = Enum.valueOf(Tim.class, "AB");
        Tim[] tims = Tim.values();
        Tim.valueOf("AC");

        PackageManager pm = getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
            System.out.println(info.packageName + " : " + info.versionName + ":" + info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    static enum Tim {

        AB(3) {
            @Override
            public int getCurState() {
                return this.state * 30;
            }
        },
        AC(2) {
            @Override
            public int getCurState() {
                return this.state * 20;
            }
        },
        AD(1) {
            @Override
            public int getCurState() {
                return this.state * 10;
            }
        };
        public final int state;

        private Tim(int n) {
            this.state = n;
        }
        public abstract int getCurState();

    }

    private void say() {
        System.out.println("admin");
    }

    public void onEvent(Activity activity) {
        System.out.println("onEvent:" + activity.getTitle() + "," + Thread.currentThread().getName());
    }
    public void onFFEventxxx(Activity activity, int aa) {
        System.out.println("onEvent:" + activity.getTitle() + "," + Thread.currentThread().getName());
    }

    public void onEventMainThread(Activity activity) {
        System.out.println("onEventMainThread:" + activity.getTitle() + "," + Thread.currentThread().getName());
    }

    public void onEventBackgroundThread(Activity activity) {
        System.out.println("onEventBackgroundThread:" + activity.getTitle() + "," + Thread.currentThread().getName());
    }

    public void onEventAsync(Activity activity) {
        System.out.println("onEventAsync:" + activity.getTitle() + "," + Thread.currentThread().getName());
    }

    public void onEventAsync(StoneEvent event) {
        System.out.println("onEventAsync:StoneEvent" + event.getCurrentEvent() + "," + event.getData());
        Looper.prepare();
        switch (event.getCurrentEvent()) {
            case StoneEvent.EVENT_RECYCLER_BACK_TO_MAIN:
                Toast.makeText(this, "刚从Recycler页面回来", Toast.LENGTH_SHORT).show();
                break;
            case StoneEvent.EVENT_CARD_BACK_TO_MAIN:
                Toast.makeText(this, "刚从Card页面回来", Toast.LENGTH_SHORT).show();
                break;
        }
        Looper.loop();

    }

    public int getLevel() {
        return 88;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy onEventAsync");
        EventBus.getDefault().unregister(this);
    }

    public void recycler(View v) {
        startActivity(new Intent(this, RecyclerActivity.class));
        ((Button) v).setText("点击了Recycler");
    }

    public void card(View v) {
        startActivity(new Intent(this, CardActivity.class));
    }
}
