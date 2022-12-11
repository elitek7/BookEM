package com.edevs.bookem;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
public abstract class Helper {
    public static void storeUser(Context context, User user) {

        // Stores a user in the shared preferences

        Log.i("Store User", user.toString());
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);// .getSharedPreferences("com.edevs.bookem", Context.MODE_PRIVATE);
        sp.edit().putInt(Constants.Users.USER_ID, user.getUserId()).apply();
        sp.edit().putString(Constants.Users.EMAIL, user.getEmail()).apply();
        sp.edit().putString(Constants.Users.USERNAME, user.getUsername()).apply();
        sp.edit().putString(Constants.Users.PASSWORD, user.getPassword()).apply();
    }
}
