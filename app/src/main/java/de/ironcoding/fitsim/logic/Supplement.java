package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 29.04.2017.
 */

public class Supplement extends Nutrition {

    private static final int TYPE_INT = 1;

    private static final float SUPP_DURATION = 1.0F;

    public Supplement(String name, float proteine, float carbs, float fat, int minLevel) {
        super(name, proteine, carbs, fat, minLevel, TYPE_INT, SUPP_DURATION);
    }

    @Override
    protected boolean isAccepted(Body body) {
        return body.canEat();
    }
}
