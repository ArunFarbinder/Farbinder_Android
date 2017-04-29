package com.example.arunmannuru.arun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArunMannuru on 4/25/17.
 */

public class CommunityActivity extends AppCompatActivity{


    JSONObject jsonObject=new JSONObject();

        public static ArrayList<String> Id=new ArrayList<String> ();

    public static ArrayList<String> Title2 = new ArrayList<String>();
    public static ArrayList<String> Description = new ArrayList<String>();
    public static ArrayList<String> Pictures=new ArrayList <String> ();



    public static ArrayAdapter<String> pfAdapter;
    ListView show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        show= (ListView) findViewById(R.id.lv_pf);
       // pfAdapter = new CommunityAdapter(CommunityActivity.this, R.layout.communityfeed_adapter, Description);
        pfAdapter = new CommunityAdapter(CommunityActivity.this,R.layout.communityfeed_adapter,Pictures);
        showData(pfAdapter);
    }


    public void showData(ArrayAdapter<String> adapter){
        adapter.notifyDataSetChanged();
        show.setAdapter(adapter);
    }

    public void ShowProfile(View view) {
        Intent intent=new Intent(CommunityActivity.this,Profile.class);
        startActivity(intent);

    }

    public void ShowSearch(View view){
        Intent intent=new Intent(CommunityActivity.this,Search.class);
        startActivity(intent);
    }

    public void ShowSpecial(View view){
        Intent intent = new Intent(CommunityActivity.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }
    public void ShowCalendar(View view){
        Intent intent = new Intent(CommunityActivity.this,CalendarActivity.class);
        startActivity(intent);
    }


}
