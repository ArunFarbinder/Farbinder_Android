package com.example.arunmannuru.arun;

/**
 * Created by ArunMannuru on 4/21/17.
 */

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.arunmannuru.arun.Utilities.API_KEY;
import static com.example.arunmannuru.arun.Utilities.SECRET_KEY;

/**
 * Created by Belal on 11/22/2015.
 */
public class Upload {

    private int serverResponseCode;

    public String upLoad2Server(String sourceFileUri) {
        Log.d("aaaa","dupLoad2Server"+sourceFileUri);


        // +"token=t&uploadfile=true&email=a@&expiry=c&logstatus=d";

        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        File sourceFile = new File(fileName);
        if (!sourceFile.isFile()) {
            Log.e("Huzza", fileName);
            Log.e("Huzza", sourceFileUri);
            Log.e("Huzza", "Source File Does not exist");
            return null;
        }

        try {

            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL("http://farbinder-dev.elasticbeanstalk.com/api/v1/upload");


            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("X-apikey", API_KEY);
            conn.setRequestProperty("X-secret", SECRET_KEY);
            conn.setRequestProperty("X-filename", fileName);

            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("myFile", fileName);
            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"myFile\"; filename=\" " + fileName + "\""   + lineEnd);
            dos.writeBytes(lineEnd);


            bytesAvailable = fileInputStream.available();
            Log.i("Huzza", "Initial .available : " + bytesAvailable);

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];




            bytesRead = fileInputStream.read(buffer, 0, bufferSize);


            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();

            fileInputStream.close();
            dos.flush();
            dos.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (serverResponseCode == 200) {
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn
                        .getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
            } catch (IOException ioex) {
            }
            return sb.toString();
        }else {
            return "Could not upload";
        }
    }
}

