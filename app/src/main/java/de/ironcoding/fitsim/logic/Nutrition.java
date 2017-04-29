package de.ironcoding.fitsim.logic;

import de.ironcoding.fitsim.repository.ITypedItem;
import de.ironcoding.fitsim.util.GameTimeUtil;

/**
 * Created by larsl on 12.04.2017.
 */

public abstract class Nutrition extends BaseLevelItem implements ITypedItem {

    private final String name;

    private final float proteine;

    private final float carbs;

    private final float fat;

    private final int typeId;

    private final float duration;

    public Nutrition(String name, float proteine, float carbs, float fat, int minLevel, int type, float duration) {
        super(minLevel);
        if (name == null) {
            name = "";
        }

        if (duration < 0) {
            duration = 0;
        }
        if (duration > GameTimeUtil.MAX_DURATION) {
            duration = GameTimeUtil.MAX_DURATION;
        }
        this.name = name;
        this.proteine = proteine;
        this.carbs = carbs;
        this.fat = fat;
        this.typeId = type;
        this.duration = duration;
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

    public long getDuration() {
        return GameTimeUtil.durationInMillis(duration);
    }

    protected void consume(Body.Stats stats, Body.Fitness fitness) {
        if (duration > 0) {
            stats.setSaturated(true);
        }
    }

    protected abstract boolean isAccepted(Body body);

}
