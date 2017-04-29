package com.example.arunmannuru.arun;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by ArunMannuru on 4/22/17.
 */

public class SearchFeedActivity extends FragmentActivity implements OnMapReadyCallback{



    private GoogleMap mMap;

    JSONObject jsonObject=new JSONObject();

    public static ArrayList<String> BusinessTitle = new ArrayList<String>();
    public static ArrayList<String> Distance = new ArrayList<String>();
    public static ArrayList<String> Address = new ArrayList<String>();
    public static ArrayList<String> Location=new ArrayList <String> ();
    public static ArrayList<String> State=new ArrayList<String>();
    public static ArrayList<String> ZipCode=new ArrayList<String>();
    public static ArrayList<String> Latitude = new ArrayList<>();
    public static ArrayList<String> Longitude = new ArrayList<>();


    public static ArrayAdapter<String> pfAdapter1;
    public static ArrayAdapter<String> pfAdapter2;
    public static ArrayAdapter<String> pfAdapter3;
    public static ArrayAdapter<String> pfAdapter4;
    public static ArrayAdapter<String> pfAdapter5;
    public static ArrayAdapter<String> pfAdapter6;
    ListView show;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        show= (ListView) findViewById(R.id.lv_pf);
        pfAdapter1 = new SearchFeedAdapter(SearchFeedActivity.this, R.layout.searchfeed_adapter,BusinessTitle);
        showData(pfAdapter1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//       for(int i=0;i < Latitude.size()&& i < Longitude.size();i++){
//           LatLng searchMap = new LatLng(Double.parseDouble(Latitude.get(i)),Double.parseDouble(Longitude.get(i)));
//           mMap.addMarker(new MarkerOptions().position(searchMap).title("Marker in Sydney"));
//           mMap.moveCamera(CameraUpdateFactory.newLatLng(searchMap));
//
//       }
        // Add a marker in Sydney and move the camera
        LatLng searchMap = new LatLng(-100.00,302.00);
        mMap.addMarker(new MarkerOptions().position(searchMap).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(searchMap));
        mMap.addMarker(new MarkerOptions().position(searchMap).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(searchMap));
    }

    public void showData(ArrayAdapter<String> adapter){
        adapter.notifyDataSetChanged();
        show.setAdapter(adapter);
    }


    public void ShowProfile(View view) {
        Intent intent=new Intent(SearchFeedActivity.this,Profile.class);
        startActivity(intent);

    }

    public void ShowCalendar(View view){
        Intent intent=new Intent(SearchFeedActivity.this,CalendarActivity.class);
        startActivity(intent);
    }

    public void ShowSpecial(View view){
        Intent intent = new Intent(SearchFeedActivity.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }
    public void ShowCommunity(View view){
        Intent intent = new Intent(SearchFeedActivity.this,CommunityActivity.class);
        startActivity(intent);
    }



}



