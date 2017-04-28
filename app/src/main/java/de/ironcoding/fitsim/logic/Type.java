package de.ironcoding.fitsim.logic;

import android.text.TextUtils;

/**
 * Created by larsl on 28.04.2017.
 */

public class Type {

    private final int id;

    private final String name;

    public Type(int id, String name) {
        this.id = id;
        if (TextUtils.isEmpty(name)) {
            name = "";
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
