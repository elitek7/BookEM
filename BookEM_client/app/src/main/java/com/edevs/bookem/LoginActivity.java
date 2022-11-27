package com.edevs.bookem;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Objects;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
    }

    public void signIn(View v) {
        EditText email = (EditText) findViewById(R.id.emailEdt);
        EditText password = (EditText) findViewById(R.id.passwordEdt);

        String email_input = email.getText().toString();
        String password_input = password.getText().toString();
        if(email_input.equals("") || password_input.equals(""))
        {    //Making sure the user inputs both
            Toast.makeText(getApplicationContext(), "Missing entries.", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(i);
        }
    }

    public void signUp(View v) {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }
}
