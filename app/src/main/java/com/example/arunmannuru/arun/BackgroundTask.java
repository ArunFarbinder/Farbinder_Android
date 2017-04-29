package com.example.arunmannuru.arun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.arunmannuru.arun.MainActivity.MY_PREFS_NAME;
//import static com.example.arunmannuru.arun.R.id.response;
import static com.example.arunmannuru.arun.R.id.title;
import static com.example.arunmannuru.arun.Utilities.API_KEY;
import static com.example.arunmannuru.arun.Utilities.SECRET_KEY;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ArunMannuru on 4/15/17.
 */

public class BackgroundTask extends AsyncTask <String,Void,String> {
Context ctx;
    final ProgressDialog progressDialog ;
    BackgroundTask(Context ctx){
     this.ctx=ctx;
        progressDialog = new ProgressDialog(ctx);
    }
String method="";

    @Override
    protected String doInBackground(String... params) {

        try {

            String str=params[2].toString();
            method=params[3];
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            Log.w("Request2", "set request method");
            String userCredentials = params[1].toString();
            String body=params[0].toString();
            Log.w("Request2", "body: "+body);



            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            Log.w("Request2", "content type");

            httpURLConnection.setDoInput(true);
            if(!method.equals("login")) {

                Log.w("Request2", "logn");

                httpURLConnection.setRequestProperty("Cookie", userCredentials);
            }
            if(method.equals("upload")) {
                httpURLConnection.setRequestProperty("X-apikey", API_KEY);
                httpURLConnection.setRequestProperty("X-secret", SECRET_KEY);
                httpURLConnection.setRequestProperty("X-filename", "testname.jpg");
            }
            OutputStream OS=httpURLConnection.getOutputStream();
            Log.w("Request2", "os");

            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data= params[0].toString();

            Log.d("Request2_",data);
            Log.d("Upload:=",data);
            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();

            Log.d("Request2_header","Cookie:"+userCredentials);

            Log.d("Request2_body", body);

            final StringBuilder output = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            int status = httpURLConnection.getResponseCode();
            Log.d("Request2_status", status+"");
            if (method.equals("login") && status == HttpURLConnection.HTTP_OK || method.equals("signup")) {
                String header = httpURLConnection.getHeaderField("Set-Cookie");

                Map<String, List<String>> header2=    httpURLConnection.getHeaderFields();

                try {
                    Log.d("lll",header2.toString());
                    String largeString=header2.toString();
                    String[] Sid=largeString.split("Set-Cookie=");

                    Log.d("abcd",Sid[1]);

                    String sid=Sid[1].substring(1);
                    String []    sid2=sid.split(";");

                    Log.d("abcd_sid",sid);
                    Log.d("abcd_sid2",sid2[0]);



                    Log.d("asdf",header);

                    String[] out = header.split(";");
                    Log.i("TAG", "first=" + out[0]);
                    String string2 = out[0];

                    SharedPreferences.Editor editor = ctx.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    // editor.putString("session_id", string2);
                    editor.putString("Set-Cookie",sid2[0]);
                    editor.putString("lid",string2);
                    editor.commit();



                }catch(NullPointerException n){
                    n.printStackTrace();
                }

            }
            StringBuilder responseOutput = new StringBuilder();

            while((line = br.readLine()) != null ) {
                responseOutput.append(line);
            }
            br.close();
            output.append(responseOutput.toString());
            MainActivity.ResponseCode = httpURLConnection.getResponseCode();

            Log.w("Request2", "resonse code " + MainActivity.ResponseCode);
            Log.w("Request2", "resonse code " + output.toString());
            return output.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


        return "excception_occured";
    }

    @Override
    protected void onPreExecute() {


        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Logging you in...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.dismiss();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        Log.d("Request2_onpostexe",s);
    if(method.equals("login")){

    JSONObject parentObject;
    try {
        parentObject = new JSONObject(s);

        Log.i("TAG", "code: " + parentObject.getString("code"));
        LoginPOJO.setCode(parentObject.getString("code"));
        Log.i("TAG", "status: " + parentObject.getString("status"));
        LoginPOJO.setStatus(parentObject.getString("status"));
        Log.i("TAG", "message: " + parentObject.getString("message"));
        LoginPOJO.setMessage(parentObject.getString("message"));

        // Getting the data from Data Json Object
        JSONObject dataObject = parentObject.getJSONObject("data");
        Log.d("newval_data",dataObject.toString());

        JSONObject json=new JSONObject(String.valueOf(dataObject));
        String uid=json.getString("id");
        Log.d("newval",uid);
        String firstname=json.getString("firstName");
        String lastname=json.getString("lastName");
        String shortname=json.getString("shortName");
        String email=json.getString("email");
        String location=json.getString("location");
        SharedPreferences.Editor editor = ctx.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        editor.putString("firstName",firstname);
        editor.putString("lastName",lastname);
        editor.putString("shortName",shortname);
        editor.putString("email",email);
        editor.putString("location",location);
        editor.commit();


    }catch (JSONException e) {
        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
        e.printStackTrace();
    }


    BackgroundTask backgroundTask= new BackgroundTask(ctx);
    JSONObject jsonObject=new JSONObject();

    try {
        jsonObject.put("apikey", API_KEY);
        jsonObject.put("secret", SECRET_KEY);
        jsonObject.put("location","teaneck");
        jsonObject.put("combined",true);
        jsonObject.put("partner_offers",true);

    } catch (JSONException e) {
        e.printStackTrace();
    }
    SharedPreferences prefs3 =ctx.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    //final String str = prefs3.getString("session_id", "");
    final String str = prefs3.getString("Set-Cookie","");

    backgroundTask.execute(jsonObject.toString(),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/get_promotions_feed/","get_promo");

    }
    else if(method.equals("request_password_reset")){

        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();

        JSONObject parentObject;
        try {
            parentObject = new JSONObject(s);

            Log.i("TAG2", "message: " + parentObject.getString("message"));
            LoginPOJO.setMessage(parentObject.getString("message"));

        }
        catch (JSONException e) {
            Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        super.onPostExecute(s);
    }

    else if(method.equals("signup")){

        JSONObject parentObject;
        try{

            parentObject = new JSONObject(s);

            BackgroundTask backgroundTask= new BackgroundTask(ctx);
            JSONObject jsonObject=new JSONObject();

            try {
                jsonObject.put("apikey", API_KEY);
                jsonObject.put("secret", SECRET_KEY);
                jsonObject.put("location","teaneck");
                jsonObject.put("combined",true);
                jsonObject.put("partner_offers",true);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences prefs3 =ctx.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            //final String str = prefs3.getString("session_id", "");
            final String str = prefs3.getString("Set-Cookie","");

            backgroundTask.execute(jsonObject.toString(),str,"http://farbinder-dev.elasticbeanstalk.com/api/v1/get_promotions_feed/","get_promo");



        }catch (JSONException e){
            Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }
       else if(method.equals("get_promo")){
            JSONObject parentObject;
            try {  Log.d("TAGG",s.toString());
                parentObject = new JSONObject(s);


                BackgroundTask backgroundTask= new BackgroundTask(ctx);
                JSONObject jsonObject=new JSONObject();

                Log.i("TAG2", "code: " + parentObject.getString("code"));
                LoginPOJO.setCode(parentObject.getString("code"));
                Log.i("TAG2", "status: " + parentObject.getString("status"));
                LoginPOJO.setStatus(parentObject.getString("status"));
                Log.i("TAG2", "message: " + parentObject.getString("message"));
                LoginPOJO.setMessage(parentObject.getString("message"));

                // Getting the data from Data Json Object

                JSONObject dataObject = parentObject.getJSONObject("data");

                JSONArray arrJson= dataObject.getJSONArray("feed");
                String[] arr=new String[arrJson.length()];
                for(int i=0;i<arrJson.length();i++) {
                    arr[i] = arrJson.getString(i);
                    Log.d("asdata2",arr[i].toString());
                }

                //first obj
                JSONObject jobj=new JSONObject(arr[0]);

                Log.i("TAG2", "id: " +jobj.getString("id"));
                Log.i("TAG2", "businessName: " + jobj.getString("businessName"));
                Log.i("TAG2", "businessLogoUrl: " + jobj.getString("businessLogoUrl"));
                Log.i("TAG2", "text: " + jobj.getString("text"));
                Log.i("TAG2", "photoUrl: " + jobj.getString("photoUrl"));
                Log.i("TAG2", "videoUrl: " + jobj.getString("videoUrl"));
                Log.i("TAG2", "isNew: " + jobj.getString("isNew"));
                Log.i("TAG2", "type: " + jobj.getString("type"));



                PromotionsFeedActivity.Titles.add(jobj.getString("businessName"));
                PromotionsFeedActivity.Title2.add(jobj.getString("text"));
                PromotionsFeedActivity.Pictures.add(jobj.getString("photoUrl"));
                PromotionsFeedActivity.Categorie.add(jobj.getString("type"));
                PromotionsFeedActivity.videoURL.add(jobj.getString("videoUrl"));
                PromotionsFeedActivity.PartnerId.add("null");
                PromotionsFeedActivity.Id.add(jobj.getString("id"));

                for(int i=1;i<arr.length; i++) {

                 JSONObject job=new JSONObject(arr[i]);


                 PromotionsFeedActivity.Titles.add(job.getString("title"));
                 PromotionsFeedActivity.Title2.add(job.getString("brand"));
                  PromotionsFeedActivity.Pictures.add(job.getString("imageUrl"));
                  PromotionsFeedActivity.Categorie.add(job.getString("type"));
                 PromotionsFeedActivity.PartnerId.add(job.getString("partnerId"));
                  PromotionsFeedActivity.Id.add(job.getString("id"));

}
                ctx.startActivity(new Intent(ctx, PromotionsFeedActivity.class));

        } catch (JSONException e) {
                Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            super.onPostExecute(s);
    }else if(method.equals("get_calendar")){
        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
    }

    else if(method.equals("get_community")){


        JSONObject parentObject;
        try {
            parentObject = new JSONObject(s);

            Log.i("TAG2", "code: " + parentObject.getString("code"));
            LoginPOJO.setCode(parentObject.getString("code"));
            Log.i("TAG2", "status: " + parentObject.getString("status"));
            LoginPOJO.setStatus(parentObject.getString("status"));
            Log.i("TAG2", "message: " + parentObject.getString("message"));
            LoginPOJO.setMessage(parentObject.getString("message"));

            // Getting the data from Data Json Object
            JSONObject dataObject = parentObject.getJSONObject("data");

            JSONArray arrJson= dataObject.getJSONArray("messageBoards");
            String[] arr=new String[arrJson.length()];
            for(int i=0;i<arrJson.length();i++) {
                arr[i] = arrJson.getString(i);
                Log.d("asdata2",arr[i].toString());
//                JSONObject jobj=new JSONObject(arr[i]);
//
//                CommunityActivity.Title2.add(jobj.getString("name"));
//                CommunityActivity.Description.add(jobj.getString("description"));
//                CommunityActivity.Pictures.add(jobj.getString("photoUrl"));
//                CommunityActivity.Id.add(jobj.getString("id"));

            }

            JSONObject jobj=new JSONObject(arr[0]);
            //CommunityActivity.Title2.add(jobj.getString("name"));
            //CommunityActivity.Description.add(jobj.getString("description"));
            CommunityActivity.Pictures.add(jobj.getString("photoUrl"));
            //CommunityActivity.Id.add(jobj.getString("id"));

//first obj
//            JSONObject jobj=new JSONObject(arr[0]);
//
//            Log.i("TAG2", "id: " +jobj.getString("id"));
//            Log.i("TAG2", "name: " + jobj.getString("name"));
//            Log.i("TAG2", "description: " + jobj.getString("description"));
//            Log.i("TAG2", "photoUrl: " + jobj.getString("photoUrl"));
//            Log.i("TAG2", "newMessagesCount: " + jobj.getString("newMessagesCount"));
//            Log.i("TAG2", "visibility: " + jobj.getString("visibility"));
//            Log.i("TAG2", "discoverability: " + jobj.getString("discoverability"));
//            Log.i("TAG2", "postability: " + jobj.getString("postability"));
//            Log.i("TAG2", "type: " + jobj.getString("type"));
//
//
//            CommunityActivity.Titles.add(jobj.getString("name"));
//            CommunityActivity.Title2.add(jobj.getString("description"));
//            CommunityActivity.Pictures.add(jobj.getString("photoUrl"));
//            CommunityActivity.Categorie.add(jobj.getString("type"));
//            CommunityActivity.videoURL.add(jobj.getString("newMessagesCount"));
//            CommunityActivity.Id.add(jobj.getString("id"));

//            for(int i=1;i<arr.length; i++) {
//
//                JSONObject job=new JSONObject(arr[i]);
//
//
//                CommunityActivity.Titles.add(job.getString("name"));
//                CommunityActivity.Title2.add(job.getString("description"));
//                CommunityActivity.Pictures.add(job.getString("photoUrl"));
//                CommunityActivity.Categorie.add(job.getString("type"));
//                CommunityActivity.PartnerId.add(job.getString("newMessagesCount"));
//                CommunityActivity.Id.add(job.getString("id"));
//
//            }
            ctx.startActivity(new Intent(ctx, CommunityActivity.class));

        } catch (JSONException e) {
            Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        super.onPostExecute(s);
    }else if(method.equals("request_invite_code")){

        Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();

    }
    else if (method.equals("get_business_profile")){

            Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
        }

        else if(method.equals("upload")){
    Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();
    Log.d("asdfgh2",s);
    }
   else if(method.equals("claim_special")){
        Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();

    }else if(method.equals("geo_lookup")){

        Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();

    }else if(method.equals("post_event")){
        Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();
    }
    else if(method.equals("post_news")){
        Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();

    }else if (method.equals("search")){

        JSONObject parentObject;

        try {
            parentObject = new JSONObject(s);

            Log.i("TAG2", "code: " + parentObject.getString("code"));
            LoginPOJO.setCode(parentObject.getString("code"));
            Log.i("TAG2", "status: " + parentObject.getString("status"));
            LoginPOJO.setStatus(parentObject.getString("status"));
            Log.i("TAG2", "message: " + parentObject.getString("message"));
            LoginPOJO.setMessage(parentObject.getString("message"));

            // Getting the data from Data Json Object
            JSONObject dataObject = parentObject.getJSONObject("data");

            JSONArray arrJson= dataObject.getJSONArray("partnerResults");
            JSONArray arrJsonResult = dataObject.getJSONArray("results");

            int arrJsonResultsLength = arrJson.length();
            int arrJsonPartnerResultsLength = arrJsonResult.length();
            int length = arrJsonResultsLength+arrJsonPartnerResultsLength;

            String[] arrResutls = new String[length];
            for(int i=0; i < arrJsonResult.length();i++){
                arrResutls[i] = arrJsonResult.getString(i);
                Log.d("Results:",arrResutls[i].toString());
            }
            String[] arr=new String[arrJson.length()];
            for(int i=0;i<arrJson.length();i++) {
                arr[i] = arrJson.getString(i);
                Log.d("asdata2",arr[i].toString());
            }



            for(int i=0; i < arrJson.length(); i++) {
                JSONObject jobj = new JSONObject(arr[i]);

                Log.i("TAG2", "id: " + jobj.getString("id"));
                Log.i("TAG2", "name: " + jobj.getString("name"));
                Log.i("TAG2", "distance: " + jobj.getString("distance"));
                Log.i("TAG2", "address: " + jobj.getString("address"));
                Log.i("TAG2", "city: " + jobj.getString("city"));
                Log.i("TAG2", "state: " + jobj.getString("state"));
                Log.i("TAG2", "zip: " + jobj.getString("zip"));
                Log.i("TAG2", "latitude: " + jobj.getString("latitude"));
                Log.i("TAG2", "longitude: " + jobj.getString("longitude"));
                //Log.i("TAG2", "logoUrl: " + jobj.getString("logoUrl"));
                Log.i("TAG2", "type: " + jobj.getString("type"));


                SearchFeedActivity.BusinessTitle.add(jobj.getString("name"));
                SearchFeedActivity.Distance.add(jobj.getString("distance"));
                SearchFeedActivity.Address.add(jobj.getString("address"));
                SearchFeedActivity.Location.add(jobj.getString("city"));
                SearchFeedActivity.State.add(jobj.getString("state"));
                SearchFeedActivity.ZipCode.add(jobj.getString("zip"));
            }



            for(int i=0; i < arrJsonResult.length(); i++) {
                JSONObject jobj = new JSONObject(arrResutls[i]);

                Log.i("TAG2", "id: " + jobj.getString("id"));
                Log.i("TAG2", "name: " + jobj.getString("name"));
                Log.i("TAG2", "distance: " + jobj.getString("distance"));
                Log.i("TAG2", "address: " + jobj.getString("address"));
                Log.i("TAG2", "city: " + jobj.getString("city"));
                Log.i("TAG2", "state: " + jobj.getString("state"));
                Log.i("TAG2", "zip: " + jobj.getString("zip"));
                Log.i("TAG2", "latitude: " + jobj.getString("latitude"));
                Log.i("TAG2", "longitude: " + jobj.getString("longitude"));
                //Log.i("TAG2", "logoUrl: " + jobj.getString("logoUrl"));
                Log.i("TAG2", "type: " + jobj.getString("type"));


                SearchFeedActivity.BusinessTitle.add(jobj.getString("name"));
                SearchFeedActivity.Distance.add(jobj.getString("distance"));
                SearchFeedActivity.Address.add(jobj.getString("address"));
                SearchFeedActivity.Location.add(jobj.getString("city"));
                SearchFeedActivity.State.add(jobj.getString("state"));
                SearchFeedActivity.ZipCode.add(jobj.getString("zip"));
            }

            ctx.startActivity(new Intent(ctx, SearchFeedActivity.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        super.onPostExecute(s);

        Toast.makeText(ctx,s,Toast.LENGTH_LONG).show();
    }
    }
}
