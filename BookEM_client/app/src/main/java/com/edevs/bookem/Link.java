package com.edevs.bookem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Link {
    // Middle ground between client and server

    public static void authenticateUser(Context context, String username, String password, TextView error_box) {

        Relay relay = new Relay(Constants.APIs.AUTHENTICATE_LOGIN, response -> authenticateUserRESPONSE(context, response, error_box), (api, e) -> error(api, context, e, "Error Connecting to Server"));

        relay.setConnectionMode(Relay.MODE.POST);

        relay.addParam(Constants.Users.USERNAME, username);
        relay.addParam(Constants.Users.PASSWORD, password);

        relay.sendRequest();

    }

    private static void authenticateUserRESPONSE(Context context, Response response, TextView error_box) {

        if (response.isAuthenticated()) {

            User user = (User) response.getQueryResult().get(Constants.Response.Classes.USER).get(0);
            assert user != null;
            Helper.storeUser(context, user);
            Intent i = new Intent(context, FeedActivity.class);
            context.startActivity(i);

        } else {

            error_box.setText("Invalid Credentials");

        }

    }


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

//    public static void getAllResourcesStoreInTempAndUpdateFeed(Context context, SwipeRefreshLayout layout, ListView list, int user_id) {
//
//        Relay relay = new Relay(Constants.APIs.GET_ALL_RESOURCES, response -> getAllResourcesStoreInTempAndUpdateFeedRESPONSE(context, response, layout, list), (api, e) -> error(api, context, e, "Error Fetching from Server"));
//
//        relay.setConnectionMode(Relay.MODE.GET);
//
//        relay.addParam(Constants.Users.USER_ID, user_id);
//
//
//        relay.sendRequest();
//
//    }

    public static void getAllImages(Context context, ResourcesAdapter layout, ListView list) {

        Relay relay = new Relay(Constants.APIs.GET_IMAGES, response -> getAllImagesRESPONSE(context, response, layout, list), (api, e) -> error(api, context, e, "Error Fetching from Server"));

        relay.setConnectionMode(Relay.MODE.GET);

        relay.sendRequest();

    }

    private static void getAllImagesRESPONSE(Context context, Response response, ResourcesAdapter layout, ListView list) {

        ArrayList<String> resources_result = (ArrayList<String>) response.getQueryResult().get("Images");
        ArrayList<ImageView> images = new ArrayList<>();
        assert resources_result != null;
        Collections.reverse(resources_result);

        //((ResourcesAdapter) list.getAdapter()).flush();
        for (int i = 0; i < resources_result.size(); i++) {
            //Temp.TEMP_RESOURCES.put(resource.getResourceId(), resource);
            //((ResourcesAdapter) list.getAdapter()).add(resource);
            //list.setAdapter(list.getAdapter());
            Bitmap img = ImageEncoding.convertToBitmap(resources_result.get(i));
            layout.add(new Resource(i, Constants.names[i], "", img));
        }
        list.setAdapter(layout);
        //layout.setRefreshing(false);
    }

    public static void getAllReservationsOnResource(Context context, SwipeRefreshLayout layout, ListView list) {

        Relay relay = new Relay(Constants.APIs.GET_ALL_RESERVATIONS_ON_RESOURCE, response -> getAllReservationsOnResourceRESPONSE(context, response, layout, list), (api, e) -> error(api, context, e, "Error Fetching from Server"));

        relay.setConnectionMode(Relay.MODE.GET);

        relay.sendRequest();

    }

    private static void getAllReservationsOnResourceRESPONSE(Context context, Response response, SwipeRefreshLayout layout, ListView list) {

        ArrayList<Reservation> reservations_result = (ArrayList<Reservation>) response.getQueryResult().get(Constants.Response.Classes.RESERVATION);

        assert reservations_result != null;
        Collections.reverse(reservations_result);

        //((ReservationsAdapter) list.getAdapter()).flush();
        reservations_result.forEach(reservation -> {

            // Temp.TEMP_RESERVATIONS.put(reservation.getReservationId(), reservation);
            //((ReservationsAdapter) list.getAdapter()).add(reservation);
            //list.setAdapter(list.getAdapter());
            //ImageView image = list.findViewById(R.id.resourcePlaceholder);
            // image.setImageBitmap(ImageEncoding.convertToBitmap(Constants.APIs.GET_IMAGES));
        });
        layout.setRefreshing(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void addReservation(Context context, LocalDate fromDate, LocalDate toDate, AppCompatActivity activity) {

        Relay relay = new Relay(Constants.APIs.ADD_RESERVATION, response -> addReservationRESPONSE(context, response, activity), (api, e) -> error(api, context, e, "Error making reservation"));

        relay.setConnectionMode(Relay.MODE.POST);

        relay.addParam(Constants.Reservations.OWNER_ID, PreferenceManager.getDefaultSharedPreferences(context).getInt(Constants.Users.USER_ID, -1));
        relay.addParam(Constants.Reservations.START_DATE, fromDate);
        relay.addParam(Constants.Reservations.END_DATE, toDate);
        relay.addParam("reservation_id", 0);


        relay.sendRequest();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void addReservationRESPONSE(Context context, Response response, AppCompatActivity activity) {

        Reservation reservation = (Reservation) Objects.requireNonNull(response.getQueryResult().get(Constants.Response.Classes.RESERVATION)).get(0);
        Temp.TEMP_RESERVATIONS.put(reservation.getReservationId(), reservation);
        Temp.TEMP_LATEST_RESERVATION = reservation.getReservationId();
        activity.finish();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void error(String api, Context context, Exception e, String error_message) {

        StringBuilder result = new StringBuilder();
        Arrays.stream(e.getStackTrace()).forEach(t -> result.append(t).append("\n"));
        Log.i(String.format("Error in API %s in %s", api, e.getMessage()), String.valueOf(result));
        ContextCompat.getMainExecutor(context).execute(() -> Toast.makeText(context, error_message, Toast.LENGTH_SHORT).show());

    }

    public static void getAllReservationsByUserStoreInTempAndUpdateList(Context context, int owner_id, ListView list, SwipeRefreshLayout layout) {

        Relay relay = new Relay(Constants.APIs.GET_ALL_RESERVATIONS_BY_USER, response -> getAllReservationsByUserStoreInTempAndUpdateListRESPONSE(context, response, list, layout), (api, e) -> error(api, context, e, "Error fetching data from the server"));

        relay.setConnectionMode(Relay.MODE.GET);
        relay.addParam(Constants.Reservations.OWNER_ID, owner_id);
        relay.addParam(Constants.Users.USER_ID, PreferenceManager.getDefaultSharedPreferences(context).getInt(Constants.Users.USER_ID, -1));
        relay.sendRequest();


    }

    private static void getAllReservationsByUserStoreInTempAndUpdateListRESPONSE(Context context, Response response, ListView list, SwipeRefreshLayout layout) {

        ArrayList<Reservation> reservations = (ArrayList<Reservation>) response.getQueryResult().get(Constants.Response.Classes.RESERVATION);
        Collections.reverse(reservations);
        reservations.forEach(gem -> Temp.TEMP_RESERVATIONS.put(gem.getReservationId(), gem));

        ReservationsAdapter adapter = new ReservationsAdapter(context, reservations);
        list.setAdapter(adapter);
        layout.setRefreshing(false);
    }

    public static void deleteReservation(Context context, int owner_id, ListView list) {

        Relay relay = new Relay(Constants.APIs.DELETE_RESERVATION, response -> deleteGemRESPONSE(context, response, owner_id, list), (api, e) -> error(api, context, e, "Error deleting reservation"));

        relay.setConnectionMode(Relay.MODE.GET);

        relay.addParam(Constants.Reservations.RESERVATION_ID, owner_id);

        relay.sendRequest();

    }

    private static void deleteGemRESPONSE(Context context, Response response, int owner_id, ListView list) {

        ((ReservationsAdapter) list.getAdapter()).remove(Temp.TEMP_RESERVATIONS.containsKey(owner_id) ? Temp.TEMP_RESERVATIONS.get(owner_id) : Temp.TEMP_RESERVATIONS.get(owner_id));
        Temp.TEMP_RESERVATIONS.remove(owner_id);
        list.invalidateViews();

    }
}
