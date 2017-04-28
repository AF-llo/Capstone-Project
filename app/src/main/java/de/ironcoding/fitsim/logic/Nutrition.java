package de.ironcoding.fitsim.logic;

import de.ironcoding.fitsim.repository.ITypedItem;

/**
 * Created by larsl on 12.04.2017.
 */

public class Nutrition extends BaseLevelItem implements ITypedItem {

    private final String name;

    private final float proteine;

    private final float carbs;

    private final float fat;

    private final int typeId;

    public Nutrition(String name, float proteine, float carbs, float fat, int minLevel, int type) {
        super(minLevel);
        if (name == null) {
            name = "";
        }
        this.name = name;
        this.proteine = proteine;
        this.carbs = carbs;
        this.fat = fat;
        this.typeId = type;
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

    public Type getType() {
        return new Type(typeId, name);
    }

    protected void consume(Body.Stats stats, Body.Fitness fitness) {}

}
