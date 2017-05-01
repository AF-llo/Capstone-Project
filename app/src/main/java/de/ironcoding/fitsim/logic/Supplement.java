package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 29.04.2017.
 */

public class Supplement extends Nutrition {

    public static final int TYPE_INT = 2;

    public Supplement(String name, float proteine, float carbs, float fat, int minLevel, float saturationDuration) {
        super(name, proteine, carbs, fat, minLevel, TYPE_INT, saturationDuration);
    }

    @Override
    public boolean isAccepted(Body body) {
        return body.canEat();
    }
}
