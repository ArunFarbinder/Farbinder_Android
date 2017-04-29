package com.example.arunmannuru.arun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;


import java.io.IOException;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

public class PromotionsFeedActivity extends AppCompatActivity implements View.OnClickListener{
    JSONObject jsonObject=new JSONObject();
    public static ArrayList<String> Id=new ArrayList<String> ();

    public static ArrayList<String> Titles = new ArrayList<String>();
    public static ArrayList<String> Title2 = new ArrayList<String>();
    public static ArrayList<String> Categorie = new ArrayList<String>();
    public static ArrayList<String> videoURL=new ArrayList<String> ();
    public static ArrayList<String> Pictures=new ArrayList <String> ();

    public static ArrayList<String> PartnerId=new ArrayList<String>();



    public static ArrayAdapter<String> pfAdapter;
    ListView show;
    VideoView videoView;
    ImageView btnWatchVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions_feed);

        btnWatchVideo = (ImageView) findViewById(R.id.btnWatchVideo);
        btnWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PromotionsFeedActivity.this,MediaActivity.class));
            }
        });


        show= (ListView) findViewById(R.id.lv_pf);
        pfAdapter = new PromotionFeedAdapter(PromotionsFeedActivity.this, R.layout.livefeed_adapter, Title2);
        pfAdapter = new PromotionFeedAdapter(PromotionsFeedActivity.this,R.layout.livefeed_adapter,Titles);
        showData(pfAdapter);

        String data = LoginPOJO.getCode() + "\n" + LoginPOJO.getStatus() + "\n" + LoginPOJO.getMessage();


    }
    public void showData(ArrayAdapter<String> adapter){
        adapter.notifyDataSetChanged();
        show.setAdapter(adapter);
    }


    public void ShowProfile(View view) {
        Intent intent=new Intent(PromotionsFeedActivity.this,Profile.class);
        startActivity(intent);

    }
    public void ShowSpecial(View view){
        Intent intent = new Intent(PromotionsFeedActivity.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }

    public void ShowSearch(View view){
        Intent intent=new Intent(PromotionsFeedActivity.this,Search.class);
        startActivity(intent);
    }

    public void ShowCalendar(View view){
        Intent intent = new Intent(PromotionsFeedActivity.this,CalendarActivity.class);
        startActivity(intent);
    }
    public void ShowCommunity(View view){
        getCommunity();
        Intent intent = new Intent(PromotionsFeedActivity.this,CommunityActivity.class);
        startActivity(intent);
    }
    public void ShowVidoUrl(View view){
        Intent intent = new Intent(PromotionsFeedActivity.this, MediaActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnComunity: {
                break;
            }
//            case R.id.btnWatchVideo:{
//                 videoView.start();
//                 break;
//            }
        }
    }

    private void getCommunity() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "18313");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(PromotionsFeedActivity.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_community", "get_community");
    }
}
