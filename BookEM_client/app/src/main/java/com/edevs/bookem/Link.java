package com.edevs.bookem;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
public class Link {
    // Middle ground between client and server

    public static void checkAvailability(Context context, User user, TextView error_box) {

        Relay relay = new Relay(Constants.APIs.IS_USERNAME_EMAIL_AVAILABLE, response -> checkAvailabilityRESPONSE(context, response, user, error_box), (api, e) -> error(api, context, e, "Error Connecting to Server"));

        relay.setConnectionMode(Relay.MODE.POST);

        relay.addParam(Constants.Users.USERNAME, user.getUsername());
        relay.addParam(Constants.Users.EMAIL, user.getEmail());

        relay.sendRequest();

    }

    private static void checkAvailabilityRESPONSE(Context context, Response response, User user, TextView error_box) {

        int availability = response.isAvailable();

        if (availability == Constants.Response.Availability.NONE_AVAILABLE || availability == Constants.Response.Availability.EMAIL_AVAILABLE) {

            error_box.setText(R.string.username_taken);

        } else if (availability == Constants.Response.Availability.USERNAME_AVAILABLE) {

            error_box.setText(R.string.email_taken);

        } else {

            addUserToDatabase(context, user);

        }

    }

    public static void addUserToDatabase(Context context, User user) {

        Relay relay = new Relay(Constants.APIs.ADD_USER, response -> addUserToDatabaseRESPONSE(context, response, user), (api, e) -> error(api, context, e, "Error Connecting to Server"));

        relay.setConnectionMode(Relay.MODE.POST);

        relay.addParam(Constants.Users.USERNAME, user.getUsername());
        relay.addParam(Constants.Users.PASSWORD, user.getPassword());
        relay.addParam(Constants.Users.EMAIL, user.getEmail());

        relay.sendRequest();

    }

    private static void addUserToDatabaseRESPONSE(Context context, Response response, User user) {

        Intent intent = new Intent(context, FeedActivity.class);
        user.setUserId(response.getLastId());
        Helper.storeUser(context, user);
        context.startActivity(intent);

    }

    public static void getAndStoreUser(Context context, int user_id) {

        Relay relay = new Relay(Constants.APIs.GET_USER, response -> getAndStoreUserRESPONSE(context, response), (api, e) -> error(api, context, e, "Error Fetching User from Server"));

        relay.setConnectionMode(Relay.MODE.GET);

        relay.addParam(Constants.Users.USER_ID, user_id);

        relay.sendRequest();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void getAndStoreUserRESPONSE(Context context, Response response) {

        User result = (User) Objects.requireNonNull(response.getQueryResult().get(Constants.Response.Classes.USER)).get(0);
        assert result != null;
        Helper.storeUser(context, result);
        Temp.TEMP_USERS.put(result.getUserId(), result);
    }


}
