package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by larsl on 01.05.2017.
 */

public class JsonActivity {

    @SerializedName("id")
    private int id;

    @SerializedName("pal")
    private Float pal;

    @SerializedName("effort")
    private Integer effort;

    @SerializedName("experience")
    private Integer experience;

    @SerializedName("name")
    private String name;

    @SerializedName("duration")
    private Float duration;

    @SerializedName("typeid")
    private Integer typeId;

    @SerializedName("muscleid")
    private Integer muscleId;

    @SerializedName("level")
    private Integer minLevel;

    public int getId() {
        return id;
    }

    public float getPal() {
        return pal;
    }

    public int getEffort() {
        return effort;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public float getDuration() {
        return duration;
    }

    public int getTypeId() {
        return typeId;
    }

    public Integer getMuscleId() {
        return muscleId;
    }

    public Integer getMinLevel() {
        return minLevel;
    }
}
