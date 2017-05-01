package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by larsl on 30.04.2017.
 */

public class JsonMuscle {


    @SerializedName("muscleId")
    private Integer muscleId;

    @SerializedName("name")
    private String name;

    @SerializedName("limit")
    private Integer limit;

    public Integer getMuscleId() {
        return muscleId;
    }


    public String getName() {
        return name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setMuscleId(Integer muscleId) {
        this.muscleId = muscleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
