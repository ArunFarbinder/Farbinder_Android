package com.example.arunmannuru.arun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

        import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int ResponseCode;
    private List<String> stringList;
    private ListView list;
    private LoginPOJO jsonResponse;
    EditText etUserName;
    EditText etPassword;
    TextView btnSkipLoginForNow;


    public static String MY_PREFS_NAME="my_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        jsonResponse = new LoginPOJO();

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        ImageView btnLogin = (ImageView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        TextView btForgot_password = (TextView) findViewById(R.id.btForgot_password);
        btForgot_password.setOnClickListener(this);

        ImageView btnSignUp = (ImageView) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

        TextView btnSkipLoginForNow = (TextView) findViewById(R.id.btnSkipLoginForNow);
        btnSkipLoginForNow.setOnClickListener(this);


    }



    private void logIn(final String userName, final String password) {

        JSONObject jsonObject=new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("email", userName);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
      //  final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask=new BackgroundTask(MainActivity.this);
        backgroundTask.execute(jsonObject.toString(),"","http://farbinder-dev.elasticbeanstalk.com/api/v1/login","login");

    }

    private void skipForNow() {

        JSONObject jsonObject=new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("email", "demo@farbinder.com");
            jsonObject.put("password", "fbDemoUser123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask=new BackgroundTask(MainActivity.this);
        backgroundTask.execute(jsonObject.toString(),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/login","login");

    }


    private void forGotPassword(final String userName) {

        JSONObject jsonObject=new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("email", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask=new BackgroundTask(MainActivity.this);
        backgroundTask.execute(jsonObject.toString(),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/request_password_reset","request_password_reset");



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin: {
                String username = etUserName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                logIn(username, password);
                break;
            }
            case R.id.btForgot_password:{
                String username = etUserName.getText().toString().trim();
                forGotPassword(username);
                break;
            }
            case R.id.btnSignUp:{
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnSkipLoginForNow:{
                skipForNow();
                break;
            }

        }
    }
}