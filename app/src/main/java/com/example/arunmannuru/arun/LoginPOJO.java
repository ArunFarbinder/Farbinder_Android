package com.example.arunmannuru.arun;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ArunMannuru on 3/24/17.
 */

public class LoginPOJO {

    // just show me one or two I will do rest
    // sure
   private static String code;
    private static String status;
    private static String message;

 public static String getDataId() {
  return dataId;
 }

 public static void setDataId(String dataId) {
  LoginPOJO.dataId = dataId;
 }

 private static String dataId;
    private String dataFirstName;
    private String dataLastName;
    private String dataShortName;
    private String dataEmail;
    private String dataRole;
    private String dataInviteMessage;
    private String dataVerified;
    private String dataZipCode;
    private static String dataLocation;
    private String dataType;

    private String geoLatitude;
    private String geoLongitude;
    private String geoZipCode;
    private String geoCity;
    private String geoState;
    private String geoType;

    private String businessId;
    private String businessName;
    private String businessType;

    private String metaUserVideoUrl;
    private String metaBusinessVideoUrl;
    private String metaPromoteVideoUrl;
    private String metaSearchVideoUrl;
    private String metaFaqUrl;
    private String metaPrivacyUrl;
    private String metaTermsUrl;
    private String metaContactFormUrl;
    private String metaFeedbackFormUrl;
    private String metaType;

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        LoginPOJO.code = code;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        LoginPOJO.status = status;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        LoginPOJO.message = message;
    }
}
