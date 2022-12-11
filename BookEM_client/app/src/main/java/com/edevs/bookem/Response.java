package com.edevs.bookem;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {

    // Holds the response from the server

    private final String error; // The error code (if any)
    private final boolean success; // The success status
    private final boolean is_authenticated; // Whether an authentication was successful
    private final int last_id; // The last inserted id
    private final HashMap<String, ArrayList<?>> query_result; // The results of a query
    private final int is_available; // Whether an availability has been detected

    public Response(int last_id, String error, boolean success, boolean is_authenticated, HashMap<String, ArrayList<?>> query_result, int is_available) {

        // Constructor
        this.last_id = last_id;
        this.error = error;
        this.success = success;
        this.is_authenticated = is_authenticated;
        this.query_result = query_result;
        this.is_available = is_available;

    }
}
