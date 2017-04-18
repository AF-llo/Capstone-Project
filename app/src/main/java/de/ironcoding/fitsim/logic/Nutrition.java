package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Nutrition extends BaseLevelItem {

    private final String name;

    private final float proteine;

    private final float carbs;

    private final float fat;

    public Nutrition(String name, float proteine, float carbs, float fat, int minLevel) {
        super(minLevel);
        if (name == null) {
            name = "";
        }
        this.name = name;
        this.proteine = proteine;
        this.carbs = carbs;
        this.fat = fat;
    }

    public String getName() {
        return name;
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
