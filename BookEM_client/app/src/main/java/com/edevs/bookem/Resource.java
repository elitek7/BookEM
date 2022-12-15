package com.edevs.bookem;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Resource {

    protected int resource_id;
    protected String name;
    protected String description;
    protected Bitmap img;

    public Resource(int resource_id, String name, String description, Bitmap img)
    {
        this.resource_id = resource_id;
        this.name = name;
        this.img = img;
        this.description = description;
    }

    public int getResourceId() {return resource_id; }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }
    public String getName() {return name; }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {return description; }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
            return "Resource{"+"Resource ID= " + resource_id
                    + "Resource Name= " + name
                    + "Resource Description= " + description + "}";
    }
}
