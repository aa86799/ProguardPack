package com.stone.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 16/3/14 23 18
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acti_main);

        ImageView iv = (ImageView) findViewById(R.id.iv_img);

        config();

        String url = "";
//        Glide.with(this).load(R.drawable.aa).into(iv);

        loadImage(iv, R.drawable.aa);
    }

    private void config() {
        MemorySizeCalculator calculator = new MemorySizeCalculator(this); //默认由该类计算内存缓存size
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); //自动计算出的默认缓存size
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();   //bitmap pool size

        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH); //动态设置内存缓存size
    }

    private void loadImage(ImageView iv, int resid) {
        Glide
                .with(this)
//                .load(url)
                .load(resid)
//                .fitCenter() //缩放在view中，图的中心点与view的中心点匹配  全图可见
                .centerCrop()  //缩放，一边贴view的边，就停止
                .placeholder(R.mipmap.ic_launcher) //占位图
//                .error(resid)  //错误图
                .crossFade() //淡入
                .override(1000, 800) //图片尺寸
                .transform(new BitmapTransformation(this) {
                    @Override
                    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
                        Bitmap bitmap = Bitmap.createBitmap(toTransform, 0, 300, outWidth, outHeight-300);
                        return bitmap;
                    }

                    @Override
                    public String getId() {
                        return "aa";
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.RESULT) //ALL=SOURCE+RESULT  SOURCE原图尺寸 RESULT所以转化后的尺寸 NONE不缓存
                .into(iv);
    }
}
