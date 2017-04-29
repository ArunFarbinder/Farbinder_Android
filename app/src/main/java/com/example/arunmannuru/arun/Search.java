package com.example.arunmannuru.arun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.password;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;
import static com.example.arunmannuru.arun.R.id.etPassword;
import static com.example.arunmannuru.arun.R.id.etWhat;
import static com.example.arunmannuru.arun.R.id.etWhere;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Search extends AppCompatActivity implements View.OnClickListener{


    EditText etWhat;
    EditText etWhere;
    ImageView btnWatchVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




        etWhat = (EditText) findViewById(R.id.etWhat);
        etWhere = (EditText) findViewById(R.id.etWhere);

        btnWatchVideo = (ImageView) findViewById(R.id.btnWatchVideo);
        btnWatchVideo.setOnClickListener(this);

        ImageView btnSearch = (ImageView) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch: {
                String what = etWhat.getText().toString().trim();
                String where = etWhere.getText().toString().trim();
                searchIt(what,where);
                break;
            }
            case R.id.btnWatchVideo:{
                Intent intent = new Intent(Search.this,MediaActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void getBusinessProfile() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_business_profile", "get_business");
    }

    private void searchIt(final String what,final String where) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("what", what);
            jsonObject.put("where", where);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/search", "search");
    }

    private void getUserProfile() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "5pFIxZECrj");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_user_profile", "get_user_profile");
    }

    public void ShowSearchFeed(View view){
        Intent intent=new Intent(Search.this,SearchFeedActivity.class);
        startActivity(intent);
    }

    //claim special----------------------------------------
    private void claimSpecial() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/claim_special", "claim_special");
    }

//claim special----------------------------------------
//post news--------------------

    private void postnews() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");
            jsonObject.put("news", "Hello");//what we give to this sir

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_news", "post_news");
    }

    //delete news-----------------------------------

    private void deleteNews() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("news_id - id", "3");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_news", "delete_news");
    }

    private void postReview() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");
            jsonObject.put("review", "verygood");
            jsonObject.put("rating", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_review", "post_review");
    }


    private void postEvent() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("community_id", "3");
            jsonObject.put("name", "balu");
            jsonObject.put("description", "very");
            jsonObject.put("start", "2017-04-26 10:30:00Z");
            jsonObject.put("end", "2017-05-01 10:30:00Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_event", "post_event");
    }


       private void deleteEvent() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("community_id", "4");
            jsonObject.put("event_id", "234");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_event", "delete_event");
    }

    private void getCategories() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_categories", "get_categories");
    }

    private void postSpecial() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");
            jsonObject.put("title", "hello");
            jsonObject.put("description", "how");
            jsonObject.put("expires", "2017-04-12");
            jsonObject.put("media_uuid", Utilities.SECRET_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_special", "post_special");
    }

    private void deleteSpecial() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("special_id", "90");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_special", "delete_special");
    }

    private void stopSpecial() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("special_id", "90");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/stop_special", "stop_special");
    }

    private void getCalender() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("location", "teaneck");
            jsonObject.put("start", "2017-04-24 10:30:00Z");
            jsonObject.put("end", "2017-04-25 10:30:00Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_calendar", "get_calendar");
    }

    private void followBusiness() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/follow_business", "follow_business");
    }

    private void unfollowBusiness() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/unfollow_business", "unfollow_business");
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

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_community", "get_community");
    }

    private void getMessageBoard() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("community_id", "72");
            jsonObject.put("board_id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_message_board", "get_message_board");
    }


    private void getMessage() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("message_id", "72");
            jsonObject.put("board_id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_message", "get_message");
    }

    private void flagMessage() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("message_id", "72");
            jsonObject.put("board_id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/flag_message", "flag_message");
    }


    private void setDefaultCommunity() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/set_default_community", "set_default_community");
    }

    private void joinCommunity() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/join_community", "join_community");
    }

    private void leaveCommunity() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/leave_community", "leave_community");
    }

    private void postMessage() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("community_id", "72");
            jsonObject.put("board_id", "72");
            jsonObject.put("subject", "72");
            jsonObject.put("message", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_message", "post_message");
    }

    private void sendPrivateReply() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("community_id", "72");
            jsonObject.put("board_id", "72");
            jsonObject.put("message_id", "72");
            jsonObject.put("message", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/send_private_reply", "send_private_reply");
    }

    private void postReply() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("community_id", "72");
            jsonObject.put("board_id", "72");
            jsonObject.put("message_id", "72");
            jsonObject.put("message", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_reply", "post_reply");
    }

    private void deleteMessage() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("community_id", "72");
            jsonObject.put("board_id", "72");
            jsonObject.put("message_id", "72");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_message", "delete_message");
    }
    private void findCommunities() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("zipcode", "07699");
            jsonObject.put("location", "teaneck");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/find_communities", "find_communities");
    }
    private void suggestMessageBoard() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("communityid", "07699");
            jsonObject.put("description", "teaneck");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/suggest_message_board", "suggest_message_board");
    }

    private void geoLookup() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("zipcode", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/geo_lookup", "geo_lookup");
    }

    private void deleteCoverPhoto() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "07699");
            jsonObject.put("photo_id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_cover_photo", "delete_cover_photo");
    }
    private void deleteGalleryPhoto() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "07699");
            jsonObject.put("photo_id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_gallery_photo", "delete_gallery_photo");
    }

    private void getCuratedlisttopics() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("zipcode", "07699");
            jsonObject.put("location", "teaneck");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_curated_list_topics", "get_curated_list_topics");
    }

    private void deleteAccount() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_account", "delete_account");
    }

    private void disconnectBusiness() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/disconnect_business", "disconnect_business");
    }

    private void addEventFilter() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "07699");
            jsonObject.put("user_id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/add_event_filter", "add_event_filter");
    }

    private void removeEventFilter() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "07699");
            jsonObject.put("user_id", "07699");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/remove_event_filter", "remove_event_filter");
    }

    private void requestInviteCode() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/request_invite_code", "request_invite_code");
    }

    private void updateVisibilityFlag() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", Utilities.SECRET_KEY);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/update_visibility_flags", "update_visibility_flags");
    }

    private void requestPassword() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("email", "arun@farbinder.com");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/request_password_reset", "request_password_reset");
    }

    private void sendFeedback() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("message", "arun@farbinder.com");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/send_feedback_message", "send_feedback_message");
    }

    private void sendContactMessage() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("message", "arun@farbinder.com");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/send_contact_message", "send_feedback_message");
    }

    private void postReviewResponse() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "arun@farbinder.com");
            jsonObject.put("review_id", "arun@farbinder.com");
            jsonObject.put("response", "arun@farbinder.com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_review_response", "post_review_response");
    }

    private void deleteReviewResponse() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "arun@farbinder.com");
            jsonObject.put("review_id", "arun@farbinder.com");
            jsonObject.put("response", "arun@farbinder.com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_review_response", "delete_review_response");
    }

    private void deleteReview() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "arun@farbinder.com");
            jsonObject.put("review_id", "arun@farbinder.com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_review", "delete_review");
    }

    private void editSpecial() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "arun@farbinder.com");
            jsonObject.put("special_id", "arun@farbinder.com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/edit_special", "edit_special");
    }

    private void checkInvite() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("code", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/check_invite_code", "check_invite_code");
    }

    private void suggestCurated() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("description", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/suggest_curated_list_topic", "suggest_curated_list_topic");
    }

    private void getUserLikes() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_user_likes", "get_user_likes");
    }

    private void getItemLikes() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "com");
            jsonObject.put("type", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/get_item_likes", "get_item_likes");
    }

    private void likeItem() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "com");
            jsonObject.put("type", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/like_item", "like_item");
    }

    private void unlikeItem() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "com");
            jsonObject.put("type", "com");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Search.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/unlike_item", "unlike_item");
    }


    public void ShowProfile(View view) {
        Intent intent=new Intent(Search.this,Profile.class);
        startActivity(intent);

    }

    public void ShowCalendar(View view){
        Intent intent=new Intent(Search.this,CalendarActivity.class);
        startActivity(intent);
    }

    public void ShowSpecial(View view){
        Intent intent = new Intent(Search.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }
    public void ShowCommunity(View view){
        Intent intent = new Intent(Search.this,CommunityActivity.class);
        startActivity(intent);
    }



}
