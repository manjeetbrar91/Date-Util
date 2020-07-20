package com.mj.dateutillib;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Utility {
    Context context;


    public Utility() {

    }

    public Utility(Context context) {
        this.context = context;

    }

    public static String SERVER_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";//2020-02-25 10:49:09
    public static String SERVER_DATE_FORMAT = "yyyy-MM-dd";//2020-02-25 10:49:09
    public static String LOCAL_DATE_FORMAT = "MMM dd, yyyy hh:mm a";//2020-02-25 10:49:09


    @SuppressLint("SimpleDateFormat")
    public String convertDate(String oldDate) {
        SimpleDateFormat serverSDF = new SimpleDateFormat(SERVER_DATETIME_FORMAT);
        SimpleDateFormat localSDF = new SimpleDateFormat(LOCAL_DATE_FORMAT);
        try {

            Date date = serverSDF.parse(oldDate);
            return localSDF.format(date);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return oldDate;
    }

    public static float dpToPixel(Context context, float dip) {

        Resources r = context.getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
    }

    @SuppressLint("MissingPermission")
    public static Boolean isInternetOn(Context context) {
        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        } catch (Exception e) {
            return false;
        }
    }



    @SuppressLint("SimpleDateFormat")
    public String convertDateToServerFormat(Date toDate) {
        try {
            return new SimpleDateFormat(SERVER_DATE_FORMAT).format(toDate);
        } catch (Exception e) {
            return "";

        }
    }

    public void copyToClipBoard(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
        }
    }

    private static final String SERVER_DOB_FMT = "dd-MM-yyyy";

    @SuppressLint("SimpleDateFormat")
    public Date parseDOB(String signup_dob) {
        try {
            return new SimpleDateFormat(SERVER_DATE_FORMAT).parse(signup_dob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    @SuppressLint("SimpleDateFormat")
    public String formatDOB(Date dob) {
        try {
            return new SimpleDateFormat(SERVER_DOB_FMT).format(dob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("SimpleDateFormat")
    public String formatDOBStringTOString(String signup_dob) {
        try {
            Date date = new SimpleDateFormat(SERVER_DOB_FMT).parse(signup_dob);
            return new SimpleDateFormat(SERVER_DATE_FORMAT).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signup_dob;
    }

    public String generateOTP() {
        return generateOTP(4);
    }

    public String generateOTP(int maxLen) {
        StringBuffer otp = new StringBuffer();

        int i = 0;
        Random r = new Random();
        do {
            otp.append(r.nextInt(9));
            i++;
        } while (i < maxLen);

        return otp.toString();
    }


    public static boolean isValidEmail(String target) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return target.matches(emailPattern) && target.length() > 0;


    }
}
