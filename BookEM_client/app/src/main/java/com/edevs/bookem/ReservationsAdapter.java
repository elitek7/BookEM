package com.edevs.bookem;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
public class ReservationsAdapter extends ArrayAdapter<Reservation>{
    // The adapter forking all the feeds in the App
    int owner_id;

    private final Context context; // The context of the Parent feed
    SharedPreferences sp;

    private final List<Reservation> reservations_list; // The list that this feed will populate


    public ReservationsAdapter(@NonNull Context context, @NonNull List<Reservation> reservations_list) {

        super(context, 0, reservations_list);
        this.context = context;
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.reservations_list = reservations_list;

    }

    public void add(@NonNull Reservation reservation) {

        // Adds a reservation to the list
        reservations_list.add(reservation);
    }

    public void insert(@NonNull Reservation reservation, int i) {

        // Inserts a reservation in the list at index i
        reservations_list.add(i, reservation);
    }

    public void flush() {

        // Empties the list
        reservations_list.clear();
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        final View listItem;

        Reservation currentReservation = reservations_list.get(position);

        if (currentReservation != null) {

            // Inflates the layout
            listItem = LayoutInflater.from(context).inflate(R.layout.reservation_item, parent, false);


           // Initializes the elements
            TextView fromTextView = listItem.findViewById(R.id.fromTextView);
            TextView toTextView = listItem.findViewById(R.id.toTextView);

            // Populates the Text

            fromTextView.setText(currentReservation.getStartDate().toString());
            toTextView.setText(currentReservation.getEndDate().toString());
            //content.setText(currentReservation.getDescription());
            //image.setImageBitmap(ImageEncoding.convertToBitmap(Constants.APIs.GET_IMAGES));

            // Retrieves the ID of the User this reservation belong to
            owner_id = sp.getInt(Constants.Users.USER_ID, -1);

        }else {
            throw new UnsupportedOperationException("Unknown Resource");

        }return listItem;

    }

    public void cancelReservation(View v)
    {
        Link.deleteReservation(context, owner_id, YourReservationsActivity.feed);
    }
}
