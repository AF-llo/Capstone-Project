package de.ironcoding.fitsim.ui.model;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 27.04.2017.
 */

public class AthleteViewModel {

    private Athlete athlete;

    public AthleteViewModel(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getSkill() {
        return athlete.getLevel().getSkill().getName();
    }

    public String getExperience() {
        return String.valueOf(athlete.getLevel().getReachedExperience());
    }

    public String getMaxExperience() {
        return String.valueOf(athlete.getLevel().getMaxExperience());
    }

    public String getLevel() {
        return String.valueOf(athlete.getLevel().getValue());
    }

    public String getEnergy() {
        return String.valueOf(athlete.getBody().getStats().getEnergy());
    }

    public Athlete getAthlete() {
        return athlete;
    }
}
