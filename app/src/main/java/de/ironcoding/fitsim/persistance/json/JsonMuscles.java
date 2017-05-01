package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by larsl on 30.04.2017.
 */

public class JsonMuscles {

    @SerializedName("muscles")
    private List<JsonMuscle> muscles;

    public List<JsonMuscle> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<JsonMuscle> muscles) {
        this.muscles = muscles;
    }
}
