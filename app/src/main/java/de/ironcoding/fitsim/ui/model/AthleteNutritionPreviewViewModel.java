package de.ironcoding.fitsim.ui.model;

import java.text.DecimalFormat;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.util.FormatUtil;

/**
 * Created by larsl on 28.04.2017.
 */

public class AthleteNutritionPreviewViewModel {

    private Athlete current;

    private Athlete preview;

    private int id;

    private DecimalFormat format;

    public AthleteNutritionPreviewViewModel(Athlete current, Athlete preview, int nutritionId) {
        this.current = current;
        this.preview = preview;
        this.id = nutritionId;
        format = FormatUtil.baseFloatForatter();
    }

    public int getId() {
        return id;
    }

    public Athlete getAthlete() {
        return current;
    }

    public String getCalories() {
        return format.format(preview.getBody().getCalories().getConsumedEnergy());
    }

    public String getChangedCalories() {
        return format.format(preview.getBody().getCalories().getConsumedEnergy() - current.getBody().getCalories().getConsumedEnergy());
    }

    public String getProteine() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedProteine());
    }

    public String getChangedProteine() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedProteine() - current.getBody().getCalories().getMacros().getConsumedProteine());
    }

    public String getCarbs() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedCarbs());
    }

    public String getChangedCarbs() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedCarbs() - current.getBody().getCalories().getMacros().getConsumedCarbs());
    }

    public String getFat() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedFat());
    }

    public String getChangedFat() {
        return format.format(preview.getBody().getCalories().getMacros().getConsumedFat() - current.getBody().getCalories().getMacros().getConsumedFat());
    }

}
