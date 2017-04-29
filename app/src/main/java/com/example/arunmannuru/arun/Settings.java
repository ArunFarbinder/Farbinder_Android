package com.example.arunmannuru.arun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.internal.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Settings extends AppCompatActivity implements View.OnClickListener {
ImageView ivCamera,iv_settings_pic;
    public static final String IMAGE_TYPE = "image/*";
    private static final int SELECT_SINGLE_PICTURE = 101;
    EditText firstname,lastname;
    TextView btnSignOut;
    TextView btnDeleteAccount;
    private WebView webView;


    private String userChoosenTask;

    int REQUEST_CAMERA = 0;
    int SELECT_FILE = 1;
    String encoded = "";
    ProgressDialog pd;

    String filePath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_adapter);
        ivCamera= (ImageView) findViewById(R.id.iv_camera);
        iv_settings_pic= (ImageView) findViewById(R.id.iv_pic_settings);
        firstname= (EditText) findViewById(R.id.et_firstname);
        lastname= (EditText) findViewById(R.id.et_lastname);
        btnSignOut = (TextView) findViewById(R.id.btnSignOut);
        btnDeleteAccount = (TextView) findViewById(R.id.btnDeleteAccount);
        this.webView = (WebView)findViewById(R.id.webview);

//        WebSettings settings = webView.getSettings();
//        settings.setJavaScriptEnabled(true);
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return true;
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//            }
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//            }
//        }
//        );

        TextView btnSignOut = (TextView) findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(this);

        TextView btnDeleteAccount = (TextView) findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(this);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
/*
                Intent intent = new Intent();
                intent.setType(IMAGE_TYPE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,0);*/


            }
        });

    }




    private void onSelectFromGalleryResult(Intent data) {

        Uri uri = data.getData();
        File file=new File(uri.getPath());
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
///*
//        try {
//            Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//          //  iv_newProfilepictrue.setImageBitmap(thumbnail);
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            Bitmap bitmap = thumbnail;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//
//            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//            Log.d("galleryencoded", encoded);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
        Log.e("Huzza", data.toString());
        Log.e("Huzza", uri.toString());
        Log.e("Huzza", file.getAbsolutePath());
        Log.e("Huzza", data.getData().getEncodedPath());

        Log.e("Huzza", "Source File Does not exist");



        Upload upload=new Upload();
        upload.upLoad2Server(file.getAbsolutePath());
    }
    private void onCaptureImageResult(Intent data) {
        Uri uri = data.getData();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        filePath = cursor.getString(columnIndex);
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        Toast.makeText(Settings.this, filePath, Toast.LENGTH_LONG).show();
        Toast.makeText(Settings.this, filename, Toast.LENGTH_LONG).show();
          //   tvPath.setText(filePath);
        cursor.close();

   /*
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
      //  iv_newProfilepictrue.setImageBitmap(thumbnail);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = thumbnail;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("cameraencoded", encoded);*/

    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};//please wait a while
        AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result = com.example.arunmannuru.arun.Utility.checkPermission(Settings.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case com.example.arunmannuru.arun.Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == SELECT_FILE)

                onSelectFromGalleryResult(data);

            else if (requestCode == REQUEST_CAMERA)

                onCaptureImageResult(data);
        }
    }

 /*   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode==0) {



            //test
            Uri uri = data.getData();
            String[] projection = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            Log.d("imagess", DatabaseUtils.dumpCursorToString(cursor));

            int columnIndex = cursor.getColumnIndex(projection[0]);
            String picturePath = cursor.getString(columnIndex); // returns null
            cursor.close();
            Log.d("imagess",picturePath);

            //test
          *//*  Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            filePath = cursor.getString(columnIndex);
//            String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
  //          Toast.makeText(Settings.this, filename, Toast.LENGTH_LONG).show();
            Log.d("newpath",filePath);
            cursor.close();
*//*

*//*
            Uri selectedImageUri = data.getData();
                try {
                    iv_settings_pic.setImageBitmap(new UserPicture(selectedImageUri, getContentResolver()).getBitmap());
                    Upload upload =new Upload();
            String stringresult=        upload.upLoad2Server(selectedImageUri.getPath());
                 //   Log.d("newresults",stringresult);
                   *//**//* Bitmap bitmapObtained = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmapObtained.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    BackgroundTask backgroundTask=new BackgroundTask(Settings.this);
                    SharedPreferences prefs3 =getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    final String str = prefs3.getString("Set-Cookie", "");

                    backgroundTask.execute(Arrays.toString(byteArray),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/upload","upload");
*//**//*              } catch (IOException e) {
                    Log.e(MainActivity.class.getSimpleName(), "Failed to load image", e);
                }
                // original code
//                String selectedImagePath = getPath(selectedImageUri);
//                selectedImagePreview.setImageURI(selectedImageUri);


        }*//*}
    }*/

    public void UpdateProfile(View view) {


    }


    private void logOUt() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "A7XBrEvbo0");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Settings.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/logout", "logout");

        Intent intent = new Intent(Settings.this,MainActivity.class);
        startActivity(intent);
    }

    private void deleteAccount() {

        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("apikey", Utilities.API_KEY);
            jsonObject.put("secret", Utilities.SECRET_KEY);
            jsonObject.put("id", "A7XBrEvbo0");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences prefs3 = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String str = prefs3.getString("Set-Cookie", "");

        BackgroundTask backgroundTask = new BackgroundTask(Settings.this);
        backgroundTask.execute(jsonObject.toString(), str, "http://farbinder-dev.elasticbeanstalk.com/api/v1/delete_account", "delete_account");

        Intent intent = new Intent(Settings.this,MainActivity.class);
        startActivity(intent);
    }



    public void ShowCalendar(View view) {
        Intent intent=new Intent(Settings.this,CalendarActivity.class);
        startActivity(intent);

    }

    public void ShowSearch(View view){
        Intent intent=new Intent(Settings.this,Search.class);
        startActivity(intent);
    }

    public void ShowSpecial(View view){
        Intent intent = new Intent(Settings.this,PromotionsFeedActivity.class);
        startActivity(intent);
    }
    public void ShowCommunity(View view){
        Intent intent = new Intent(Settings.this,CommunityActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignOut: {
                logOUt();
                break;
            }
            case R.id.btnDeleteAccount:{
                deleteAccount();
                break;
            }
            case R.id.btnBackArrow:{
                Intent intent = new Intent(Settings.this,Profile.class);
                startActivity(intent);
                break;
            }
    }
    }
}
