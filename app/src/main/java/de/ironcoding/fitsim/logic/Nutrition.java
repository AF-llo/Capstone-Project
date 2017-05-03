package de.ironcoding.fitsim.logic;

import de.ironcoding.fitsim.repository.ITypedItem;
import de.ironcoding.fitsim.util.GameTimeUtil;

/**
 * Created by larsl on 12.04.2017.
 */

public abstract class Nutrition extends BaseLevelItem implements ITypedItem {

    private final int id;

    private final String name;

    private final float proteine;

    private final float carbs;

    private final float fat;

    private final int typeId;

    private final float saturationDuration;

    public Nutrition(int id, String name, float proteine, float carbs, float fat, int minLevel, int type, float saturationDuration) {
        super(minLevel);
        if (name == null) {
            name = "";
        }

        if (saturationDuration < 0) {
            saturationDuration = 0;
        }
        if (saturationDuration > GameTimeUtil.MAX_DURATION) {
            saturationDuration = GameTimeUtil.MAX_DURATION;
        }
        this.id = id;
        this.name = name;
        this.proteine = proteine;
        this.carbs = carbs;
        this.fat = fat;
        this.typeId = type;
        this.saturationDuration = saturationDuration;
    }

    public int getId() {
        return id;
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

    public long getSaturationDuration() {
        return GameTimeUtil.gameDurationInMillis(saturationDuration);
    }

    protected void consume(Body.Stats stats, Body.Fitness fitness) {
        if (saturationDuration > 0) {
            stats.setSaturated(true);
        }
    }

    public abstract boolean isAccepted(Body body);

}
