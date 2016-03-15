package com.stone.glide.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 在manifest中配置<meta-data />
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565); //默认的

        final int size = 1024 * 1024 * 50;

        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, size));
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "pics", size));//内部缓存目录
        builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, "outpics", size));//外部sdcard缓存目录

        // If you can figure out the folder without I/O:
// Calling Context and Environment class methods usually do I/O.
        builder.setDiskCache(
                new DiskLruCacheFactory("diskCacheFolder", size)); //指定sdcard缓存目录

// In case you want to specify a cache folder ("glide"):
        builder.setDiskCache(
                new DiskLruCacheFactory("diskCacheFolder_glide", "diskCacheName", size));//sdcard缓存目录

// In case you need to query the file system while determining the folder:
        GlideBuilder diskCacheFolder = builder.setDiskCache(new DiskLruCacheFactory(new DiskLruCacheFactory.CacheDirectoryGetter() {
            @Override
            public File getCacheDirectory() {
                return new File("diskCacheFolder");
            }
        }, size));


        builder.setDiskCache(new DiskCache.Factory() {
            @Override public DiskCache build() {
                File cacheLocation = new File("diskCacheFolder");
                if (!cacheLocation.exists())
                    cacheLocation.mkdirs();
                return DiskLruCacheWrapper.get(cacheLocation, size);
            }
        });


        //----------- memory cache  -------------
        builder.setMemoryCache(new LruResourceCache(size/10));

        //----------- bitmap pool  -------------
        builder.setBitmapPool(new LruBitmapPool(size/20));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}