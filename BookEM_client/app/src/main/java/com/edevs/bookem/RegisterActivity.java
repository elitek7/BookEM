package com.edevs.bookem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    private TextView error_box; // The error box
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        // Initializes the error box
        error_box = findViewById(R.id.errorBox);

        // Initializes the edit boxes
        username = (EditText) findViewById(R.id.usernameEdt);
        email = (EditText) findViewById(R.id.emailEdt2);
        password = (EditText) findViewById(R.id.passwordEdt2);
        confirm_password = (EditText) findViewById(R.id.confirmPasswordEdt);
    }

    public void signUp(View v){



        String username_input = username.getText().toString();
        String email_input = email.getText().toString();
        String password_input = password.getText().toString();
        String confirm_password_input = confirm_password.getText().toString();


        try {

            if (username_input.length() < 2) {

                // Checks if the username is too short
                error_box.setText(R.string.short_username);

            } else if (password_input.length() < 5) {

                // Checks if the password is too weak
                error_box.setText(R.string.weak_password);

            } else if (!email_input.contains("@") || !email_input.contains(".")) {

                // Checks if the email is valid
                error_box.setText(R.string.invalid_email);

            } else if (!password_input.equals(confirm_password_input)) {

                // Checks if the passwords match
                error_box.setText(R.string.not_matching_passwords);

            } else {

                // Creates and posts the new user
                User user = new User(-1, username_input, password_input, email_input);
                Link.checkAvailability(RegisterActivity.this, user, error_box);

            }

        } catch (IllegalArgumentException e) {

            error_box.setText("error");

        }
    }

    public void signIn(View v) {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}