package com.edevs.bookem;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Relay extends AsyncTask<String, Void, String> {

    // The direct Link between the APIs and the Frontend

    private final Map<String, Object> parameters; // The parameters to be sent
    private final String url; // The url to be sent
    private final PROCESS executor; // The post executor
    private final ERROR error; // The error handler
    private final String api; // The API name
    private String mode = "GET"; // The connection mode

    public Relay(@NonNull String api, @NonNull PROCESS content, @NonNull ERROR error) {

        // Constructor
        super();
        this.api = api;
        this.url = Constants.URL.buildUrl(api);
        this.executor = content;
        this.error = error;
        this.parameters = new HashMap<>();

    }
}
