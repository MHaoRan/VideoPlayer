package com.mhr.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 加载网络图片
 */
public class BitMapUtils {
    URL imgUrl = null;
    Bitmap bitmap = null;

    public BitmapDrawable getImageBitmap(final String url) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                bitmap = (Bitmap)msg.obj;
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行访问网络操作
                try {
                    imgUrl = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }).start();
        return new BitmapDrawable(bitmap);
    }
}
