package com.edevs.bookem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);
    }

    public void signUp(View v){
        EditText username = (EditText) findViewById(R.id.usernameEdt);
        EditText email = (EditText) findViewById(R.id.emailEdt2);
        EditText password = (EditText) findViewById(R.id.passwordEdt2);
        EditText confirm_password = (EditText) findViewById(R.id.confirmPasswordEdt);


        String username_input = username.getText().toString();
        String email_input = username.getText().toString();
        String password_input = password.getText().toString();
        String confirm_password_input = confirm_password.getText().toString();


        if(username_input.equals("") || password_input.equals("") || confirm_password_input.equals("") || email_input.equals(""))
        {
            Toast.makeText(this, "Missing entries.", Toast.LENGTH_LONG).show();
        }
        else if(!password_input.equals(confirm_password_input))
        {       //Making sure password are matching
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(this, "Welcome, " + username_input, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(i);
        }
    }

    public void signIn(View v) {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}