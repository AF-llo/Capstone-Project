package de.ironcoding.fitsim.persistance.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by larsl on 01.05.2017.
 */

public class JsonNutrition {

    @SerializedName("typeid")
    private Integer typeid;

    @SerializedName("name")
    private String name;

    @SerializedName("proteine")
    private Float proteine;

    @SerializedName("carbs")
    private Float carbs;

    @SerializedName("fat")
    private Float fat;

    @SerializedName("saturationDuration")
    private Float saturationDuration;

    @SerializedName("level")
    private Integer level;

    @SerializedName("energy")
    private Integer energy;

    public Integer getTypeid() {
        return typeid;
    }

    public String getName() {
        return name;
    }

    public Float getProteine() {
        return proteine;
    }

    public Float getCarbs() {
        return carbs;
    }

    public Float getFat() {
        return fat;
    }

    public Float getSaturationDuration() {
        return saturationDuration;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getEnergy() {
        return energy;
    }

}
