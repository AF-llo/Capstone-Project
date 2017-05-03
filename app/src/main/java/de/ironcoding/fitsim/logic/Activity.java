package de.ironcoding.fitsim.logic;

import de.ironcoding.fitsim.repository.ITypedItem;
import de.ironcoding.fitsim.util.GameTimeUtil;

/**
 * Created by larsl on 12.04.2017.
 */

public abstract class Activity extends BaseLevelItem implements ITypedItem {

    private static final float BASE_ATTRACTION_FROM_EFFORT = 0.1F;

    public static final float DEFAULT_ACTIVITY_DURATION_HOURS = 0.5F;

    private final float pal;

    private final int effort;

    private final int experience;

    private final String name;

    private final float duration;

    private final int typeId;

    Activity(String name, float pal, int effort, int experience, float duration, int minLevel, int typeId) {
        super(minLevel);
        if (pal < 0) {
            pal = 0;
        }
        if (effort < 0) {
            effort = 0;
        }
        if (experience < 0) {
            experience = 0;
        }
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
        this.pal = pal;
        this.effort = effort;
        this.experience = experience;
        this.duration = duration;
        this.typeId = typeId;
    }

    Activity(String name, float pal, int effort, int experience, int minLevel, int type) {
        this(name, pal, effort, experience, DEFAULT_ACTIVITY_DURATION_HOURS, minLevel, type);
    }

    public String getName() {
        return name;
    }

    public float getPal() {
        return pal;
    }

    public int getEffort() {
        return effort;
    }

    public int getExperience() {
        return experience;
    }

    public float getDurationInHours() {
        return duration;
    }

    public long getDurationInMillis() {
        return GameTimeUtil.gameDurationInMillis(duration);
    }

    public Type getType() {
        return new Type(typeId, name);
    }

    public int getAttraction(float buildUp) {
        return (int) (buildUp * effort * BASE_ATTRACTION_FROM_EFFORT + 1);
    }

    public abstract void perform(Body.Fitness fitness, BodyType bodyType, Muscles muscles);

    public abstract boolean isToDemanding(Muscles muscles);
}
