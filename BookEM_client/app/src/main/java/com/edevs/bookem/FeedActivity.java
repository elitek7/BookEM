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
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;


public class FeedActivity extends AppCompatActivity {

    ListView feed; // The feed holding the gems
    //SwipeRefreshLayout pullToRefresh;

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
    }
}
