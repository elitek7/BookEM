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

    public static User extractUser(Context context) {

        // Retrieves a user from the shared preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        int user_id = sp.getInt(Constants.Users.USER_ID, -1);
        String username = sp.getString(Constants.Users.USERNAME, "lorem ipsum");
        String password = sp.getString(Constants.Users.PASSWORD, "lorem ipsum");
        String email = sp.getString(Constants.Users.EMAIL, "lorem_ipsum@co.com");
        return new User(user_id, username, password, email);
    }

    public static ArrayList<Resource> rebaseResourcesFromJSON(JSONArray json) {

        // Converts a JSON array to a Resource array
        ArrayList<Resource> result = new ArrayList<>();
        try {
            for (int i = 0; i < json.length(); i++) {

                JSONObject current = json.getJSONObject(i);

                int resource_id = current.getInt(Constants.Resources.RESOURCE_ID);
                int owner_id = current.getInt(Constants.Resources.OWNER_ID);
                JSONObject content = new JSONObject(current.getString(Constants.Resources.DESCRIPTION));
                Resource current_resource = null;

                        String text = content.getString(Constants.Resources.Content.TEXT);
                        current_resource = new TextResource(resource_id, mine_date, edit_date, owner_id, text, diamonds, remines, comments, root, is_liked, is_voted);


                        String img_src = content.getString(Constants.Resources.Content.IMG_SRC);
                        current_resource = new ImageResource(resource_id, mine_date, edit_date, owner_id, img_src, diamonds, remines, comments, root, is_liked, is_voted);
                }


                Log.i("RESOURCES", String.valueOf(current_resource));
                result.add(current_resource);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;

    }
}
