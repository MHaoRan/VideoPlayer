package com.mhr.activity;


import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alibaba.fastjson.JSON;
import com.example.cardvideoplayer.R;
import com.mhr.adapter.MyViewPagerAdapter;
import com.mhr.entiy.VideoBean;
import com.mhr.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends Activity {
    //接口链接相关数据
    private String url;
    private int page;   //页数
    private int count;  //一页有多少个
    private String type = "vide";    //播放的类型，默认指定为video

    //ViewPager 这里是一个页面
    private ViewPager viewPager;
    //布局项 这里main.xml 里面包裹着CardView 再里面videoview 这是一个item
    private VideoView videoView;
    private LinearLayout head_layout,foot_layout;
    private ImageView head_iv_back,head_iv_search,foot_iv_fire,foot_iv_partner,foot_iv_me,foot_iv_circle;
    private TextView head_tv_title,foot_tv_fire,foot_tv_partner,foot_tv_me,foot_tv_circle;

    //数据项
    List<VideoBean> videoBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintUrl(2,80);
        initController();
        initListenr();
        new Thread(new inintData()).start();

    }

    private void initListenr() {
        foot_tv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,UserActivity.class);
                startActivity(it);
                MainActivity.this.finish();
            }
        });

        foot_iv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,UserActivity.class);
                startActivity(it);
                MainActivity.this.finish();
            }
        });

    }

    private void initVideoView(VideoBean videoBean) {
        videoView.setVideoURI(Uri.parse(videoBean.getVideo()));
        MediaController mediaController = new MediaController(this);
        videoView.start();
    }

    private void inintUrl(int page, int count) {
        this.page = page;
        this.count = count;
        this.url = "https://api.apiopen.top/getJoke?page="+this.page+"&count="+this.count+"&type=video";
    }

    private void initController() {
        videoView = findViewById(R.id.videoView);
        viewPager = findViewById(R.id.viewPager);
        head_layout = findViewById(R.id.head_lnlayout);
        foot_layout = findViewById(R.id.foot_lnlayout);
        head_iv_back = findViewById(R.id.head_iv_back);
        head_iv_search = findViewById(R.id.head_iv_search);
        head_tv_title = findViewById(R.id.head_tv_title);
        head_tv_title.setText("推荐");
        foot_iv_fire = findViewById(R.id.foot_iv_fire);
        foot_iv_partner = findViewById(R.id.foot_iv_partner);
        foot_iv_circle = findViewById(R.id.foot_iv_circle);
        foot_iv_me = findViewById(R.id.foot_iv_me);
        foot_tv_circle = findViewById(R.id.foot_tv_circle);
        foot_tv_me = findViewById(R.id.foot_tv_me);
        foot_tv_fire = findViewById(R.id.foot_tv_fire);
        foot_tv_partner = findViewById(R.id.foot_tv_partner);

        //设置图片资源
        head_iv_back.setImageResource(R.drawable.head_go);
        head_iv_search.setImageResource(R.drawable.head_search);
        foot_iv_fire.setImageResource(R.drawable.foot_fire_select);
        foot_iv_partner.setImageResource(R.drawable.foot_plane_notselect);
        foot_iv_me.setImageResource(R.drawable.foot_user_notselect);
        foot_iv_circle.setImageResource(R.drawable.foot_chat_notselect);
    }

     private class inintData implements Runnable{
        //接受信息
        @SuppressLint("HandlerLeak") Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //msg.what对应子线程中msg的标签，在子线程中进行赋值
                if(msg.what==1){
                    videoBeans = (List<VideoBean>) msg.obj;
                    //开始设置viewpage
                    viewPager.setCurrentItem(0);
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setAdapter(new MyViewPagerAdapter(MainActivity.this,videoBeans));
//                    for(int i = 0 ; i < videoBeans.size() ; i ++ ){
//                        initVideoView(videoBeans.get(0));
//                    }
                }else {
                    Toast.makeText(MainActivity.this,"网络请求错误",Toast.LENGTH_SHORT).show();
                }
            }
        };
        @Override
        public void run() {
            HttpUtils.sendOkHttpRequest(url,new Callback(){
                @Override
                public void onFailure(Call call, IOException e) {
                    //异常处理
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String httpRs = response.body().string();
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(httpRs);
                        String code = jsonObject.getString("code");
                        if (code.equals("200")) {
                            String arr = jsonObject.getString("result");
                            if (arr.length() > 1) {
                                List<VideoBean> videoBeans = JSON.parseArray(arr, VideoBean.class);
                                Message message = new Message();
                                message.what = 1;
                                message.obj = videoBeans;
                                mHandler.sendMessage(message);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }



}
