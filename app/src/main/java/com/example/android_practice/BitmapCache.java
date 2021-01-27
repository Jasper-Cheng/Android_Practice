package com.example.android_practice;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapCache implements ImageLoader.ImageCache {
    // LruCatch是Android提供的缓存工具
    private LruCache<String, Bitmap> mCache;

    public BitmapCache() {
        // 获取系统给应用程序分配的内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 取1/8作为图片缓存
        int maxCatch = maxMemory / 8;

        // 创建缓存
        mCache = new LruCache<String, Bitmap>(maxCatch) {

            //测量Bitmap的大小 ,便于统计缓存使用总量
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getWidth() * value.getHeight();
            }
        };
    }

    /**
     * 获取缓存的图片
     * url：下载图片时传入的图片网址
     */
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    /**
     * 缓存图片，以url作为key值
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
