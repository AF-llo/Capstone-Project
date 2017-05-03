package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 17.04.2017.
 */

public class EnergyBooster extends Nutrition {

    public static final int TYPE_INT = 3;

    private final int energy;

    public EnergyBooster(int id, String name, int energy, int minLevel, float saturationDuration) {
        super(id, name, 0, 0, 0, minLevel, TYPE_INT, saturationDuration);
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
    public boolean isAccepted(Body body) {
        return true;
    }
}
