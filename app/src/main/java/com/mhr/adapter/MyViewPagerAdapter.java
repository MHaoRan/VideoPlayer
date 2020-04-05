package com.mhr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;


import com.example.cardvideoplayer.R;
import com.mhr.entiy.VideoBean;
import com.mhr.utils.BitMapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyViewPagerAdapter extends PagerAdapter {

    Context context;
    //主线程传来的数据 每一个videoBean 是一个数据集
    private List<VideoBean> videoBeans;
    //存放viewpage视频信息
    private Map<Integer,VideoView> mapVideo = new HashMap<>();
    //当前的view
    private View mCurrentView;
    //视频在哪
    int point;

    public MyViewPagerAdapter(){
    }

    public MyViewPagerAdapter(Context context,List<VideoBean> videoBeans){
            this.context = context;
            this.videoBeans = videoBeans;
    }

    @Override
    public int getCount() {
        //页卡的数量
        return videoBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        System.out.println(position+"---------------"+container.getChildCount()+"-----"+videoBeans.size());
        View view = View.inflate(context, R.layout.main_item, null);
        final VideoView currentVideoView = view.findViewById(R.id.videoView);
        VideoView beforeVideo = null;
        currentVideoView.setVideoPath(videoBeans.get(position).getVideo());
        currentVideoView.setKeepScreenOn(true);
        currentVideoView.requestFocus();
        currentVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        //设置缩率图
        BitmapDrawable imageBitmap = new BitMapUtils().getImageBitmap(videoBeans.get(position).getThumbnail());
      //  System.out.println( null == imageBitmap ? "111" : "222"); 判断bitmap是否为空
        currentVideoView.setBackground(imageBitmap);
        currentVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                currentVideoView.setBackground(null);
            }
        });

        mapVideo.put(position,currentVideoView);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        System.out.println(mapVideo.size()+"mapvideosize");

        //准备开始时
        if(mapVideo.size()  == 2){
            mapVideo.get(0).start();
            mapVideo.get(1).pause();
        }else {
            mapVideo.get(mapVideo.size()-1).pause();
            mapVideo.get(mapVideo.size()-2).start();
            mapVideo.get(mapVideo.size()-3).pause();

        }

        //向上翻时
        if(!getCurrentVideoView(mCurrentView).isPlaying()){
            getCurrentVideoView(mCurrentView).start();
            //当前位置的viedoview正在播放时
            if(point >= 1) {
                if (mapVideo.get(point).isPlaying()) {
                    mapVideo.get(point+1).pause();
                    mapVideo.get(point-1).pause();
                }
            }else {
                mapVideo.get(point+1).pause();
            }
        }


    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {


    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View)object;
        point = position;
        System.out.println(point+"这是point");
    }

    public VideoView getCurrentVideoView(View currentView){
        return currentView.findViewById(R.id.videoView);
    }
}
