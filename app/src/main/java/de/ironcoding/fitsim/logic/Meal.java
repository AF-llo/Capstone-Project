package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 28.04.2017.
 */

public class Meal extends Nutrition {

    private static final int TYPE_INT = 0;

    private static final float MEAL_DURATION = 3.0F;

    public Meal(String name, float proteine, float carbs, float fat, int minLevel) {
        super(name, proteine, carbs, fat, minLevel, TYPE_INT, MEAL_DURATION);
    }

    @Override
    protected boolean isAccepted(Body body) {
        return body.canEat();
    }
}
