package com.edevs.bookem;

import androidx.annotation.NonNull;

public class Resource {

    protected int resource_id;

    public Resource(int resource_id)
    {
        this.resource_id = resource_id;
    }

    public int getResourceId() {return resource_id; }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }
    @NonNull
    public String toString(){
            return null;
    }
}
