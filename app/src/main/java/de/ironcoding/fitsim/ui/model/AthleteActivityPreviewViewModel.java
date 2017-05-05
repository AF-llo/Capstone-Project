package de.ironcoding.fitsim.ui.model;

import java.text.DecimalFormat;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.util.FormatUtil;

/**
 * Created by larsl on 28.04.2017.
 */

public class AthleteActivityPreviewViewModel {

    private Athlete current;

    private Athlete preview;

    private int activityId;

    private DecimalFormat decimalFormat;

    public AthleteActivityPreviewViewModel(Athlete current, Athlete preview, int activityId) {
        this.current = current;
        this.preview = preview;
        this.activityId = activityId;
        decimalFormat = FormatUtil.baseFloatForatter();
    }

    public Athlete getAthlete() {
        return preview;
    }

    public String getExperience() {
        return String.valueOf(preview.getLevel().getReachedExperience());
    }

    public String getGrownExperience() {
        return String.valueOf(preview.getLevel().getTotalExperience() - current.getLevel().getTotalExperience());
    }

    public String getEffort() {
        return String.valueOf(preview.getBody().getStats().getEnergy());
    }

    public String lostEnergy() {
        return String.valueOf(preview.getBody().getStats().getEnergy() - current.getBody().getStats().getEnergy());
    }

    public int getId() {
        return activityId;
    }

    public String getStrength() {
        return decimalFormat.format(preview.getBody().getFitness().getStrength());
    }

    public String getChangedStrength() {
        return decimalFormat.format(preview.getBody().getFitness().getStrength() - current.getBody().getFitness().getStrength());
    }

    public String getStamina() {
        return decimalFormat.format(preview.getBody().getFitness().getStamina());
    }

    public String getChangedStamina() {
        return decimalFormat.format(preview.getBody().getFitness().getStamina() - current.getBody().getFitness().getStamina());
    }
}
