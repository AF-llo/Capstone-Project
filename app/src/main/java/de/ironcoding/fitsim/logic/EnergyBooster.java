package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 17.04.2017.
 */

public class EnergyBooster extends Nutrition {

    private final int energy;

    public EnergyBooster(String name, int energy, int minLevel) {
        super(name, 0, 0, 0, minLevel);
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
