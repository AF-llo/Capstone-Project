package de.ironcoding.fitsim.logic.nutrition;

import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 17.04.2017.
 */

public class EnergyBooster extends Nutrition {

    private final int energy;

    public EnergyBooster(int energy) {
        super(0, 0, 0);
        if (energy < 0) {
            energy = 0;
        }
        this.energy = energy;
    }

    @Override
    protected void consume(Body.Stats stats, Body.Fitness fitness) {
        stats.gainEnergy(energy);
    }
}
