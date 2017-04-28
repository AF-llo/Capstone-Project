package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 28.04.2017.
 */

public class Meal extends Nutrition {

    private static final int TYPE_INT = 0;

    public Meal(String name, float proteine, float carbs, float fat, int minLevel) {
        super(name, proteine, carbs, fat, minLevel, TYPE_INT);
    }

    @Override
    protected void consume(Body.Stats stats, Body.Fitness fitness) {
        super.consume(stats, fitness);
        // TODO: 28.04.2017 make athlete not hungry
    }
}
