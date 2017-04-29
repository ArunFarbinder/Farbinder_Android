package com.example.arunmannuru.arun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/25/17.
 */

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView btnPost_Event;
    ImageView btnRightArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main_page);

        getCalender();

        btnPost_Event = (ImageView) findViewById(R.id.btnPost_Event);
        btnPost_Event.setOnClickListener(this);

        btnRightArrow = (ImageView) findViewById(R.id.btnRightArrow);
        btnRightArrow.setOnClickListener(this);
    }

    public void ShowProfile(View view) {
        Intent intent=new Intent(CalendarActivity.this,Profile.class);
        startActivity(intent);

    }

    public void ShowSearch(View view){
        Intent intent=new Intent(CalendarActivity.this,Search.class);
        startActivity(intent);
    }

    public void ShowSpecial(View view){
        Intent intent = new Intent(CalendarActivity.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }
    public void ShowCommunity(View view){
        Intent intent = new Intent(CalendarActivity.this,CommunityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPost_Event:{
                Intent intent = new Intent(CalendarActivity.this,EventPost.class);
                startActivity(intent);
                break;
            }
            case R.id.btnBackArrow:{
                break;
            }
        }
    }


    private void getCalender() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("location", "teaneck");
            jsonObject.put("start", "2016-04-24 10:30:00Z");
            jsonObject.put("end", "2016-04-25 10:30:00Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(CalendarActivity.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_calendar", "get_calendar");
    }

}
