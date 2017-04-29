package com.example.arunmannuru.arun;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/26/17.
 */

public class ConnectBusinessActivity extends AppCompatActivity implements View.OnClickListener{

    TextView btnRequest_InviteCode;
    ImageView btnConnected;
    EditText etInvite_code;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_business);


        btnRequest_InviteCode = (TextView) findViewById(R.id.btnRequest_InviteCode);
        btnRequest_InviteCode.setOnClickListener(this);

        btnConnected = (ImageView) findViewById(R.id.btnConnected);
        btnConnected.setOnClickListener(this);

        etInvite_code = (EditText) findViewById(R.id.etInvite_code);
        etInvite_code.setOnClickListener(this);

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

        BackgroundTask backgroundTask = new BackgroundTask(ConnectBusinessActivity.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/request_invite_code", "request_invite_code");
    }

    private void checkInvite(final String inviteCode) {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("code", inviteCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(ConnectBusinessActivity.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/check_invite_code", "check_invite_code");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRequest_InviteCode: {
                requestInviteCode();
                break;
            }
            case R.id.btnConnected:{
                String inviteCode = etInvite_code.getText().toString().trim();
                checkInvite(inviteCode);
                break;
            }
        }
    }



}
