package com.edevs.bookem;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Temp {
    // Holds temporary values
    public static final TreeMap<Integer, Resource> TEMP_GEMS = new TreeMap<>(Comparator.reverseOrder()); // Retrieved gems
    public static int TEMP_LATEST_RESOURCE = -1; // Latest resource added
    public static final HashMap<Integer, User> TEMP_USERS = new HashMap<>(); // Retrieved users
}
