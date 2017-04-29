package com.example.arunmannuru.arun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Map;

public class ResponseActivity extends AppCompatActivity implements View.OnClickListener {

    // private final int IMAGE_REQUEST_CODE = 5;
    private ImageView ivImage;
//    private Bitmap selectedImage;
//    private final String twoHyphens = "--";
//    private final String lineEnd = "\r\n";
//    private final String boundary = "apiclient-" + System.currentTimeMillis();
//    private final String mimeType = "multipart/form-data;boundary=" + boundary;
//    private byte[] multipartBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        TextView tvResponse = (TextView) findViewById(R.id.tvStatus);
        // ivImage = (ImageView) findViewById(R.id.image);
        Button btnSelectImage = (Button) findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(this);
        Button btnUploadImage = (Button) findViewById(R.id.btnUploadImage);
        btnUploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnSelectImage: {
//                chooseImage();
//                break;
//            }
//            case R.id.btnUploadImage: {
//                //uploadImage(getStringImage(selectedImage));
//                byte[] fileData = getStringImage(selectedImage);
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                DataOutputStream dos = new DataOutputStream(bos);
//
//                try {
//                    buildPart(dos, fileData, "demoImage");
//                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//                    multipartBody = bos.toByteArray();
//                    String url = "http://farbinder-dev.elasticbeanstalk.com/api/v1/upload";
//                    MultipartRequest multipartRequest = new MultipartRequest(url, null, mimeType, multipartBody, new Response.Listener<NetworkResponse>() {
//
//                        @Override
//                        public void onResponse(NetworkResponse response) {
//
//                            Log.i("TAG", "onResponse: " + response);
//
//                        }
//                    },
//                        new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                                Log.i("TAG", "onErrorResponse: " + error);
//
//                            }
//                        });
//
//                    multipartRequest.setShouldCache(false);
//                    multipartRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                    RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//                    //Adding request to the queue
//                    requestQueue.add(multipartRequest);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                    //break;
            }
        }
    }


//    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
//        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
//        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\"; filename=\""
//                + fileName + "\"" + lineEnd);
//        dataOutputStream.writeBytes(lineEnd);
//
//        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
//        int bytesAvailable = fileInputStream.available();
//
//        int maxBufferSize = 1024 * 1024;
//        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//        byte[] buffer = new byte[bufferSize];
//
//        // read file and write it into form...
//        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//        while (bytesRead > 0) {
//            dataOutputStream.write(buffer, 0, bufferSize);
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//        }
//
//        dataOutputStream.writeBytes(lineEnd);
//    }



//    private void uploadImage(final String imageString) {
//        final ProgressDialog progressDialog = new ProgressDialog(ResponseActivity.this);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setMessage("Uploading image...");
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//        String UPLOAD_URL = "http://farbinder-dev.elasticbeanstalk.com/api/v1/upload";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//                        //Dismissing the progress dialog
//                        progressDialog.dismiss();
//                        Log.i("TAG", "onResponse: " + s);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        //Dismissing the progress dialog
//                        progressDialog.dismiss();
//
//                        //Showing snackbar
//                        Toast.makeText(ResponseActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                //Converting Bitmap to String
//
//                //Creating parameters
//                Map<String, String> params = new Hashtable<>();
//                  params.put("apikey", Utilities.API_KEY);
//                params.put("secret", Utilities.SECRET_KEY);
//                params.put("filename", "MyFile");
//
//                //returning parameters
//                return params;
//            }
//           @Override
//            public byte[] getBody() throws AuthFailureError {
//
//                byte[] body = new byte[1024];
//                try {
//                    body = imageString.getBytes("UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    Log.e("TAg", "Unable to gets bytes from JSON", e.fillInStackTrace());
//                }
//                return body;
//            }
//        };
//
//        stringRequest.setShouldCache(false);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        //Creating a Request Queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //Adding request to the queue
//        requestQueue.add(stringRequest);
//    }

//    private void chooseImage() {
//        // wait a sec okay
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMAGE_REQUEST_CODE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
//            Uri path = data.getData();
//            try {
//                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
//                ivImage.setImageBitmap(selectedImage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else Toast.makeText(ResponseActivity.this, "Image not selected", Toast.LENGTH_SHORT).show();
//    }


//   private byte[] getStringImage(Bitmap bitmap) {
//     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//      return  byteArrayOutputStream.toByteArray();
//       //return Base64.encodeToString(imageBytes, Base64.DEFAULT);
//   }
//}
