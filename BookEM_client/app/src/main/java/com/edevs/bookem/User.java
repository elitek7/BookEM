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

    // Accessors and mutators
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
