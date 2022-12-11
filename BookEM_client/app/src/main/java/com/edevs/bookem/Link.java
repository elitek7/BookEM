package com.edevs.bookem;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
}
