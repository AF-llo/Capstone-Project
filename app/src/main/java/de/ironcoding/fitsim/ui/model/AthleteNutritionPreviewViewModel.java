package de.ironcoding.fitsim.ui.model;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 28.04.2017.
 */

public class AthleteNutritionPreviewViewModel {

    private Athlete athlete;

    public AthleteNutritionPreviewViewModel(Athlete athlete) {
        this.athlete = athlete;
    }

    public Athlete getAthlete() {
        return athlete;
    }

}
