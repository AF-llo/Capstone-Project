package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 28.04.2017.
 */

public class Meal extends Nutrition {

    public static final int TYPE_INT = 1;

    public Meal(int id, String name, float proteine, float carbs, float fat, int minLevel, float saturationDuration) {
        super(id, name, proteine, carbs, fat, minLevel, TYPE_INT, saturationDuration);
    }

    @Override
    public boolean isAccepted(Body body) {
        return body.canEat();
    }
}
