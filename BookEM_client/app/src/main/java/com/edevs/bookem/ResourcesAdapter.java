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


public class ResourcesAdapter extends ArrayAdapter<Resource>{
    // The adapter forking all the feeds in the App

    private final Context context; // The context of the Parent feed
    private final List<Resource> resources_list; // The list that this feed will populate


    public ResourcesAdapter(@NonNull Context context, @NonNull List<Resource> resources_list) {

        super(context, 0, resources_list);
        this.context = context;
        this.resources_list = resources_list;

    }

    public void add(@NonNull Resource resource) {

        // Adds a gem to the list
        resources_list.add(resource);
    }

    public void insert(@NonNull Resource resource, int i) {

        // Inserts a gem in the list at index i
        resources_list.add(i, resource);
    }

    public void flush() {

        // Empties the list
        resources_list.clear();
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        final View listItem;

        Resource currentResource = resources_list.get(position);

        if (currentResource != null) {

            // Inflates the layout
            listItem = LayoutInflater.from(context).inflate(R.layout.resource_item, parent, false);


            // Initializes the elements
            TextView name = listItem.findViewById(R.id.resourceName);
            TextView content = listItem.findViewById(R.id.resourceDescription);
            ImageView image = listItem.findViewById(R.id.resourcePlaceholder);

            // Populates the Text

                name.setText(currentResource.getName());
                content.setText(currentResource.getDescription());
               image.setImageBitmap(ImageEncoding.convertToBitmap(Constants.APIs.GET_IMAGES));


        }else {
            throw new UnsupportedOperationException("Unknown Resource");

        }return listItem;

    }
}
