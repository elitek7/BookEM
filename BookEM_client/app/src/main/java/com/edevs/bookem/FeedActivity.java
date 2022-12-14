package com.edevs.bookem;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupMenu;


import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;


public class FeedActivity extends AppCompatActivity {

    ListView feed; // The feed holding the gems
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Forces a clean Full Screen layout
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_feed);

        // Initializes the feed
        feed = ((ListView) findViewById(R.id.feed));


        // Prepares the adapted for the feed
        ResourcesAdapter adapter = new ResourcesAdapter(FeedActivity.this, Helper.rebaseResourcesFromJSON());
        feed.setAdapter(adapter);

        // Initializes the layout holding the feed
        pullToRefresh = findViewById(R.id.pullToRefreshProfile);

        // Retrieves gems from the server an updates the feed once fetches
        //Link.getAllResourcesStoreInTempAndUpdateFeed(getApplicationContext(), pullToRefresh, feed, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(Constants.Users.USER_ID, -1));

        // Adds a refresh functionality to the layout
        pullToRefresh.setOnRefreshListener(this::onRefresh);

    }


    public void onRefresh() {

        // Handles the refresh
     //   Link.getAllResourcesStoreInTempAndUpdateFeed(getApplicationContext(), pullToRefresh, feed, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt(Constants.Users.USER_ID, -1));

    }




}
