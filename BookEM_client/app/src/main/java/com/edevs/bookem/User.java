package com.edevs.bookem;
import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

    // A class that holds a user

    private int user_id; // The id of the user
    private String username; // The username of the user
    private String password; // The password of the user
    private String email; // The email of the user

    public User(int user_id, String username, String password, String email) {

        // Constructor
        this.user_id = user_id;
        this.password = password;
        this.username = username;
        this.email = email;
    }
}
