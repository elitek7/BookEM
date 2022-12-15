package com.edevs.bookem;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView error_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        // Fetches the Shared Preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        // Checks if a user exists
        if (sp.contains(Constants.Users.USER_ID) && sp.contains(Constants.Users.USERNAME) && sp.contains(Constants.Users.PASSWORD) && sp.contains(Constants.Users.EMAIL)) {

            // Updates the user from the database
            Link.getAndStoreUser(LoginActivity.this, sp.getInt(Constants.Users.USER_ID, -1));

            // Opens the feed
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
            startActivity(intent);

        } else {

            // Displays the login interface
            setContentView (R.layout.activity_login);

            // Initializes the error box
            error_box = findViewById(R.id.loginErrorBox);

        }

    }

    public void signIn(View v) {
        EditText username = (EditText) findViewById(R.id.usernameEdt2);
        EditText password = (EditText) findViewById(R.id.passwordEdt);

        String username_input = username.getText().toString();
        String password_input = password.getText().toString();
        if(username_input.equals("") || password_input.equals(""))
        {    //Making sure the user inputs both
            error_box.setText(R.string.missing);
        }
        else
        {
            // Authenticates the user
            Link.authenticateUser(LoginActivity.this, username_input, password_input, error_box);
        }
    }

    public void signUp(View v) {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }
}
