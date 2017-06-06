package com.storeyworthy.prototype.Item;

/**
 * Created by Darth Bane on 6/5/2017.
 */
import static android.provider.BaseColumns._ID;

public class Item {
    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
