package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by larsl on 01.05.2017.
 */

public class JsonNutritions {

    @SerializedName("nutritions")
    private List<JsonNutrition> nutritions = null;

    public List<JsonNutrition> getNutritions() {
        return nutritions;
    }
}
