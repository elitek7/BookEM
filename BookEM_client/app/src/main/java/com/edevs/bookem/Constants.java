package com.edevs.bookem;

import java.time.LocalDate;

public class Constants {

    public static String[] names = new String[]{"Cleaning Services", "Studio", "Meeting Room", "Truck", "Video Projector"};
    public String[] descriptions = new String[]{"Outside of routine \n" +
            "cleaning services", "Photography workspace, 5th Floor", "Fits 25 people, 11th Floor", "Truck", "Epson Home Cinema 5050UB"};
    // Activity used for cleaner linking between the Client and the Server

    public static String[] getNames()
    {
        return names;
    }
    static class Users {

        // The Users column in the database
        public static final String USER_ID = "user_id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
    }

    static class Resources {

        // The Resources columns in the database

        public static final String RESOURCE_ID = "resource_id";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
        public static final String OWNER_ID = "owner_id";

       // public static final String TYPE = ;
    }

    static class Reservations {

        // The Reservations columns in the database

        public static final String RESERVATION_ID = "reservation_id";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String OWNER_ID = "owner_id";

        // public static final String TYPE = ;
    }

    static class APIs {

        // All the APIs of the app

        public static final String ADD_RESERVATION = "add_resource"; // Adds a resource to the database
        public static final String ADD_USER = "add_user"; // Adds a User to the database
        public static final String UPDATE_USER = "update_user"; // Updates a User in the database
        public static final String AUTHENTICATE_LOGIN = "authenticate_login"; // Authenticates a login
        public static final String GET_ALL_RESERVATIONS_ON_RESOURCE = "get_all_reservations_on_resource"; // Gets all reservations on a resource
        public static final String GET_ALL_RESOURCES = "get_all_resources"; // Gets all resources
        public static final String GET_ALL_USERS = "get_all_users"; // Gets all Users (WIP)
        public static final String GET_USER = "get_user"; // Gets a user from the database
        public static final String IS_USERNAME_EMAIL_AVAILABLE = "is_username_email_available"; // Checks if the email and username are available
        public static final String GET_ALL_RESERVATIONS_BY_USER = "get_all_reservations_by_user"; // Gets all reservations belonging to a user
        public static final String DELETE_RESERVATION = "delete_reservation"; // Removes a reservation from the database
        public static final String GET_IMAGES = "get_images";
    }

    static class Response {

        // The response object returned by APIs

        public static final String ERROR = "error"; // The error code (if any)
        public static final String SUCCESS = "success"; // The success status
        public static final String IS_AUTHENTICATED = "is_authenticated"; // Whether an authentication was successful
        public static final String QUERY_RESULT = "query_result"; // The results of a query
        public static final String IS_AVAILABLE = "is_available"; // Whether an availability has been detected
        public static final String LAST_ID = "inserted_id"; // The last inserted id

        static class Availability {

            // All possible Keys in an availability check

            public static final int NONE_AVAILABLE = 0;
            public static final int USERNAME_AVAILABLE = 1;
            public static final int EMAIL_AVAILABLE = 2;
            public static final int ALL_AVAILABLE = 3;

        }

        static class Classes {

            // All possible keys in a Query Result

            public static final String USER = "user";
            public static final String RESOURCE = "resource";
            public static final String RESERVATION = "reservation";
        }


    }

    static class URL {

        // The URL center

        public static final String MASTER_URL = "http://192.168.1.71/BookEM/BookEM_server";
        public static String buildUrl(String API) {
            return MASTER_URL + API + ".php";
        }

    }
}
