package com.example.arunmannuru.arun;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static android.app.PendingIntent.getActivity;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/26/17.
 */

public class EventPost extends AppCompatActivity implements View.OnClickListener{



    Context cnt;

    EditText etEventTitle;
    EditText etEventDescription;
    ImageView btnPostEvent;
    //Button btnSetDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        btnPostEvent = (ImageView) findViewById(R.id.btnPostEvent);
        btnPostEvent.setOnClickListener(this);

        etEventTitle = (EditText) findViewById(R.id.etEventTitle);
        etEventTitle.setOnClickListener(this);

        etEventDescription = (EditText) findViewById(R.id.etEventDescription);
        etEventDescription.setOnClickListener(this);


//        new SingleDateAndTimePickerDialog.Builder(cnt)
//                //.bottomSheet()
//                //.curved()
//                //.minutesStep(15)
//                .title("Simple")
//                .listener(new SingleDateAndTimePickerDialog.Listener() {
//                    @Override
//                    public void onDateSelected(Date date) {
//
//                    }
//                }).display();


    }





    @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPostEvent:
                    String name = etEventTitle.getText().toString().trim();
                    String description = etEventDescription.getText().toString().trim();
                    postEvent(name,description);
                    break;
               // case R.id.
            }
        }


    private void postEvent(final String name,final String description) {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("business_id", "72");
            jsonObject.put("community_id", "3");
            jsonObject.put("name", name);
            jsonObject.put("description", description);
            jsonObject.put("start", "2017-04-26 10:30:00Z");
            jsonObject.put("end", "2017-05-01 10:30:00Z");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        Intent intent = new Intent(EventPost.this,CalendarActivity.class);
        startActivity(intent);

        BackgroundTask backgroundTask = new BackgroundTask(EventPost.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/post_event", "post_event");
    }
}
