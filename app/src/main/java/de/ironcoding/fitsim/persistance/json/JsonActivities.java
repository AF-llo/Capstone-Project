package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by larsl on 01.05.2017.
 */

public class JsonActivities {

    @SerializedName("activities")
    private List<JsonActivity> activities;

    public List<JsonActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<JsonActivity> activities) {
        this.activities = activities;
    }

}
