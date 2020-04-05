package com.mhr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardvideoplayer.R;

public class EdtUserInfoActivity extends Activity {
    private ImageView head_iv_return,head_iv_search;
    private TextView head_tv_title;
    private EditText userinfo_edt_name,userinfo_edt_info,userinfo_edt_account;
    SharedPreferences sp ;
    SharedPreferences.Editor edit;
    String account,info,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_user_info);
        sp = getSharedPreferences("userinfo" , MODE_PRIVATE);
        initController();
        initListener();
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(EdtUserInfoActivity.this,UserActivity.class);
        startActivity(it);
        EdtUserInfoActivity.this.finish();
    }

    private void initListener() {
        head_iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userinfo_edt_name.getText().toString();
                account = userinfo_edt_account.getText().toString();
                info = userinfo_edt_info.getText().toString();

                if(name == null || name.trim().equals("")){
                    Toast.makeText(EdtUserInfoActivity.this,"姓名不要为空",Toast.LENGTH_SHORT).show();
                }else if(account == null || account.trim().equals("")){
                    Toast.makeText(EdtUserInfoActivity.this,"账号不要为空",Toast.LENGTH_SHORT).show();
                }else if(info == null || info.trim().equals("")){
                    Toast.makeText(EdtUserInfoActivity.this,"简介不要为空",Toast.LENGTH_SHORT).show();
                }else {


                    edit.putString("name", name);
                    edit.putString("account", account);
                    edit.putString("info", info);
                    edit.putBoolean("isLogin", true);
                    edit.commit();
                    Intent it = new Intent(EdtUserInfoActivity.this, UserActivity.class);
                    startActivity(it);
                    EdtUserInfoActivity.this.finish();
                }
            }
        });

        head_iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EdtUserInfoActivity.this,UserActivity.class);
                startActivity(it);
                EdtUserInfoActivity.this.finish();
            }
        });
    }

    private void initController() {
        edit = sp.edit();


        head_iv_return = findViewById(R.id.head_iv_back);
        head_iv_return.setImageResource(R.drawable.head_user_return);
        head_iv_search = findViewById(R.id.head_iv_search);
        head_iv_search.setImageResource(R.drawable.save);

        head_tv_title = findViewById(R.id.head_tv_title);
        head_tv_title.setText("编辑资料");

        userinfo_edt_account = findViewById(R.id.edtuserinfo_edt_account);
        userinfo_edt_info = findViewById(R.id.edtuserinfo_edt_info);
        userinfo_edt_name = findViewById(R.id.edtuserinfo_edt_name);

        if(sp.getBoolean("isLogin",false)) {
            userinfo_edt_account.setHint(sp.getString("account",""));
            userinfo_edt_name.setHint(sp.getString("name",""));
            userinfo_edt_info.setHint(sp.getString("info",""));
        }
    }
}
