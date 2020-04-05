package com.mhr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.cardvideoplayer.R;

import java.util.Timer;
import java.util.TimerTask;


public class WelcomeActivity extends Activity {
    private VideoView videoView;
    private TextView welcom_tv;
    private Button welcom_btn;
    private int rlen = 3;   //计时3s
    final Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initController();
        initListener();
        initVideo();

    }

    private void initListener() {

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent it = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(it);
                WelcomeActivity.this.finish();
            }
        });

        welcom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                Intent it = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(it);
                WelcomeActivity.this.finish();
            }
        });

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        welcom_btn.setText(rlen+"s");
                        rlen -- ;
                        if(rlen == -1) {
                                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            WelcomeActivity.this.finish();
                        }
                    }
                });
            }
        };
        //延迟0s  1s一次
        timer.schedule(task,0,1000);
    }

    private void initController() {
        videoView = findViewById(R.id.welcom_videoView);
        welcom_tv = findViewById(R.id.welcom_tv);
        welcom_btn = findViewById(R.id.welcom_btn);


    }

    private void initVideo() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.bg_video);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
