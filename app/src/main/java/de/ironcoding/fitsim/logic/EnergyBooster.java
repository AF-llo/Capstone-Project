package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 17.04.2017.
 */

public class EnergyBooster extends Nutrition {

    private static final int TYPE_INT = 2;

    private final int energy;

    public EnergyBooster(String name, int energy, int minLevel) {
        super(name, 0, 0, 0, minLevel, TYPE_INT, 0);
        if (energy < 0) {
            energy = 0;
        }
        this.energy = energy;
    }

    @Override
    protected void consume(Body.Stats stats, Body.Fitness fitness) {
        super.consume(stats, fitness);
        stats.gainEnergy(energy);
    }

    @Override
    protected boolean isAccepted(Body body) {
        return true;
    }
}
