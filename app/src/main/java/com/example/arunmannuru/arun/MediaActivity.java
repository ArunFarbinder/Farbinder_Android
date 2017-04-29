package com.example.arunmannuru.arun;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by ArunMannuru on 4/28/17.
 */

public class MediaActivity extends AppCompatActivity implements View.OnClickListener{

    VideoView ltVideoUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_url);
        ltVideoUrl = (VideoView) findViewById(R.id.ltVideoUrl);
        String path1 = "https://d1e6yi6s3cx2ur.cloudfront.net/videos/0/_20160316_ios-user.m4v";
        MediaController mc = new MediaController(this);
        mc.setAnchorView(ltVideoUrl);
        mc.setMediaPlayer(ltVideoUrl);
        Uri uri = Uri.parse(path1);
        ltVideoUrl.setMediaController(mc);
        ltVideoUrl.setVideoURI(uri);
        ltVideoUrl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ltVideoUrl.start();
            }
        });

        Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDone:{
                Intent intent = new Intent(MediaActivity.this,PromotionsFeedActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
