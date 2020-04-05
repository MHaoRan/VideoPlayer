package com.mhr.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.cardvideoplayer.R;
import com.google.android.material.tabs.TabLayout;
import com.mhr.adapter.MyfragmentViewpageAdapter;
import com.mhr.base.MyFraAct;
import com.mhr.base.MyFraEdt;
import com.mhr.base.MyFraLov;

import java.security.Key;
import java.util.ArrayList;

public class UserActivity extends FragmentActivity {
    private ImageView user_iv_bg;
    //fragment
    private ViewPager userViewPage;
    private ArrayList<Fragment> lists;
    private ArrayList<String> titles;
    private MyfragmentViewpageAdapter myfragmentViewpageAdapter ;
    TabLayout tablayout;

    //顶部导航
    private  ImageView head_iv_return,head_iv_search;
    private  TextView head_tv_title;
    //底部导航
    private ImageView foot_iv_support,foot_iv_circle,foot_iv_partner,foot_iv_me;
    private TextView foot_tv_support,foot_tv_circle,foot_tv_partner,foot_tv_me;
    //userinfo
    private TextView user_tv_name,user_tv_accountInfo;
    Button edtUserInfo;
    //获得个人信息
    SharedPreferences sp ;
    SharedPreferences.Editor editor;
    String name,account,info;   //多的话封装到bean里面去
    Boolean isLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        sp = this.getSharedPreferences("userinfo",MODE_PRIVATE);
        initController();
        initData();
        initEvent();
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(UserActivity.this,MainActivity.class);
        startActivity(it);
        UserActivity.this.finish();
    }



    private void initEvent() {


        userViewPage.setAdapter(new MyfragmentViewpageAdapter(getSupportFragmentManager(),lists,titles));
        tablayout.setupWithViewPager(userViewPage);

        foot_iv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserActivity.this,MainActivity.class);
                startActivity(it);
                UserActivity.this.finish();
                foot_iv_support.setImageResource(R.drawable.foot_fire_select);
            }
        });

        foot_tv_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserActivity.this,MainActivity.class);
                startActivity(it);
                UserActivity.this.finish();
                foot_iv_support.setImageResource(R.drawable.foot_fire_select);
            }
        });

        head_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserActivity.this,MainActivity.class);
                startActivity(it);
                UserActivity.this.finish();
            }
        });

        edtUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserActivity.this,EdtUserInfoActivity.class);
                startActivity(it);
                UserActivity.this.finish();
            }
        });

    }

    private void initData() {
        lists = new ArrayList<Fragment>();
        lists.add(new MyFraEdt());
        lists.add(new MyFraAct());
        lists.add(new MyFraLov());

        titles = new ArrayList<>();
        titles.add("作品 0");
        titles.add("动态 0");
        titles.add("喜欢 0");
    }

    private void initController() {

        name = sp.getString("name","");
        account = sp.getString("account","");
        info = sp.getString("info","");
        isLogin = sp.getBoolean("isLogin",false);


        user_iv_bg = findViewById(R.id.user_iv_bg);
        user_iv_bg.setImageResource(R.drawable.userme_bg);

        userViewPage = findViewById(R.id.user_viewpage);
        tablayout = findViewById(R.id.user_tabLy);

        head_iv_return = findViewById(R.id.head_iv_back);
        head_iv_return.setImageResource(R.drawable.head_user_return);
        head_iv_search = findViewById(R.id.head_iv_search);
        head_iv_search.setImageResource(R.drawable.head_user_search);

        head_tv_title = findViewById(R.id.head_tv_title);
        head_tv_title.setText("");

        foot_iv_support = findViewById(R.id.foot_iv_fire);
        foot_iv_support.setImageResource(R.drawable.foot_fire_notselect);
        foot_iv_circle = findViewById(R.id.foot_iv_circle);
        foot_iv_circle.setImageResource(R.drawable.foot_chat_notselect);
        foot_iv_partner = findViewById(R.id.foot_iv_partner);
        foot_iv_partner.setImageResource(R.drawable.foot_plane_notselect);
        foot_iv_me = findViewById(R.id.foot_iv_me);
        foot_iv_me.setImageResource(R.drawable.foot_user_select);

        foot_tv_support = findViewById(R.id.foot_tv_fire);
        foot_tv_circle = findViewById(R.id.foot_tv_circle);
        foot_tv_partner = findViewById(R.id.foot_tv_partner);
        foot_tv_me = findViewById(R.id.foot_tv_me);

        user_tv_name = findViewById(R.id.user_tv_info);
        user_tv_accountInfo = findViewById(R.id.user_tv_accountInfo);
        edtUserInfo = findViewById(R.id.user_btn_edt);
        if(isLogin){
            user_tv_name.setText(name);
            user_tv_accountInfo.setText("抖音号: "+account +" " +info);
        }

    }



    }

