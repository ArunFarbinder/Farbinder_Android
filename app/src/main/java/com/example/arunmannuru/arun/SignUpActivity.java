package com.example.arunmannuru.arun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;

/**
 * Created by ArunMannuru on 4/24/17.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{


    EditText etRetypePassword;
    EditText et_password;
    EditText et_zipcode;
    EditText et_email;
    EditText et_lastname;
    EditText et_firstname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etRetypePassword = (EditText) findViewById(R.id.etRetypePassword);
        et_password = (EditText) findViewById(R.id.et_password);
        et_zipcode = (EditText) findViewById(R.id.et_zipcode);
        et_email = (EditText) findViewById(R.id.et_email);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_firstname = (EditText) findViewById(R.id.et_firstname);

        ImageView btnBackArrow = (ImageView) findViewById(R.id.btnBackArrow);
        btnBackArrow.setOnClickListener(this);

        ImageView btnSignUp = (ImageView) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackArrow: {
                Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
               break;
            }
            case R.id.btnSignUp:{
                String firstName = et_firstname.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String zipCode = et_zipcode.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String rePassword = etRetypePassword.getText().toString().trim();
                signUp(firstName,email,password,zipCode);
                break;
            }
        }
    }

    private void signUp(final String firstName,final String email,final String password,final String zipCode) {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("name", firstName);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("zipcode", zipCode);
            jsonObject.put("device_identifier", Build.DEVICE);
            jsonObject.put("device_type", Build.TYPE);
            jsonObject.put("device_os", Build.VERSION.RELEASE);
         //   jsonObject.put("device_push_token", where);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(SignUpActivity.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/signup", "signup");
    }

}
