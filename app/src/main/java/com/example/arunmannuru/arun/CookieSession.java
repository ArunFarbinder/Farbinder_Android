package com.example.arunmannuru.arun;

import android.util.Log;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ArunMannuru on 4/9/17.
 */

public class CookieSession extends CookieHandler {

    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
        List<String> cookieStrings = new ArrayList<String>();
        if (responseHeaders != null && responseHeaders.get("Set-Cookie") != null)
            for (String cookieValue: responseHeaders.get("Set-Cookie")) {
                cookieStrings.add(cookieValue);
                Log.i("TAG","Cookieheaders="+cookieStrings);
            }
    }
    @Override
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
        return null;
    }
}
