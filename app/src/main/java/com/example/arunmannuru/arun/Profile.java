package com.example.arunmannuru.arun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

public class Profile extends AppCompatActivity {
    TextView fullname,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname= (TextView) findViewById(R.id.tvFullname);
        location= (TextView) findViewById(R.id.tvLocation);

        SharedPreferences prefs3 =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        prefs3.getString("firstName","");
        prefs3.getString("lastName","");
        prefs3.getString("shortName","");
        prefs3.getString("email","");
        prefs3.getString("location","");


fullname.setText( prefs3.getString("firstName","")+" "+ prefs3.getString("lastName",""));
        location.setText(prefs3.getString("location",""));

        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.settings);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,Settings.class);
                startActivity(intent);
            }
        });

        TextView linearLayout = (TextView) findViewById(R.id.tvBusinessProfile);
        linearLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,ConnectBusinessActivity.class);
                startActivity(intent);
            }
        });

    }



}
