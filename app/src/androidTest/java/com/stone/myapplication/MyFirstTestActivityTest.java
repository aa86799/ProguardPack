package com.stone.myapplication;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * author : stone
 * time   : 15/4/20 16 17
 * email  : aa86799@163.com
 *
 * Instrumentation 无界面的单元测试
 */
public class MyFirstTestActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;
    private Button mBtn;

    public MyFirstTestActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();//初始化 运行在构造函数后

        setActivityInitialTouchMode(true);

        mActivity = getActivity();
        mBtn = (Button) mActivity.findViewById(R.id.btn_recylerview);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();//资源清理 垃圾回收
    }

    @MediumTest
    public void testPreconditions() {
        assertNotNull("mActivity_is_null", mActivity);
        assertNotNull("mBtn is null", mBtn);
        Object obj = null;
        assertNotNull("object is null", obj);//false
    }

    @MediumTest
    public void testClickMeButton_layout() {
        final View decorView = mActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, mBtn);
        final ViewGroup.LayoutParams layoutParams = mBtn.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, RelativeLayout.LayoutParams.WRAP_CONTENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    /*
    运行几毫秒的用 @SmallTest
    100毫秒及以上，通常用@MediumTest或@LargeTests
     */
    @MediumTest
    public void testClickMeButton_clickButtonAndExpectInfoText() {
        /*
        TouchUtils 只能用于test thread
         */
        TouchUtils.clickView(this, mBtn);
        assertTrue(View.VISIBLE == mBtn.getVisibility());
        assertEquals("点击了Recycler", mBtn.getText());
    }
}
