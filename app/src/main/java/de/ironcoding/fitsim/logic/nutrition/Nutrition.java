package de.ironcoding.fitsim.logic.nutrition;

import de.ironcoding.fitsim.logic.Body;

/**
 * Created by larsl on 12.04.2017.
 */

public class Nutrition {

    private final float proteine;

    private final float carbs;

    private final float fat;

    public Nutrition(float proteine, float carbs, float fat) {
        this.proteine = proteine;
        this.carbs = carbs;
        this.fat = fat;
    }

    public float getProteine() {
        return proteine;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getFat() {
        return fat;
    }

    protected void consume(Body.Stats stats, Body.Fitness fitness) {}

}
