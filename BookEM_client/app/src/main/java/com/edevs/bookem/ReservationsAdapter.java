package com.edevs.bookem;
import android.content.Context;
import android.content.Intent;
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

    private final Context context; // The context of the Parent feed
    private final List<Reservation> reservations_list; // The list that this feed will populate


    public ReservationsAdapter(@NonNull Context context, @NonNull List<Reservation> reservations_list) {

        super(context, 0, reservations_list);
        this.context = context;
        this.reservations_list = reservations_list;

    }

    public void add(@NonNull Reservation reservation) {

        // Adds a gem to the list
        reservations_list.add(reservation);
    }

    public void insert(@NonNull Reservation reservation, int i) {

        // Inserts a gem in the list at index i
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

/*
            // Initializes the elements
            TextView name = listItem.findViewById(R.id.reservationName);
            TextView content = listItem.findViewById(R.id.reservationDescription);
            ImageView image = listItem.findViewById(R.id.reservationPlaceholder);

            // Populates the Text

            name.setText(currentReservation.getName());
            content.setText(currentReservation.getDescription());
            //image.setImageBitmap(ImageEncoding.convertToBitmap(Constants.APIs.GET_IMAGES));

*/
        }else {
            throw new UnsupportedOperationException("Unknown Resource");

        }return listItem;

    }
}
