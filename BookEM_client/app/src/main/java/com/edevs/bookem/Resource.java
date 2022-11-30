package com.edevs.bookem;

import androidx.annotation.NonNull;

public class Resource {

    protected int resource_id;
    protected String name;
    protected String description;

    public Resource(int resource_id, String name, String description)
    {
        this.resource_id = resource_id;
        this.name = name;
        this.description = description;
    }

    public int getResourceId() {return resource_id; }

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
