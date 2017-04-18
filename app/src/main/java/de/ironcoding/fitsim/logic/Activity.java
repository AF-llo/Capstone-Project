package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public abstract class Activity extends BaseLevelItem {

    private static final float BASE_ATTRACTION_FROM_EFFORT = 0.1F;

    /**
     * Default duration of an activity: 0.05h = 3min
     */
    public static final float DEFAULT_DURATION = 0.1F;

    public static final float MAX_DURATION = 24.0F;

    private static final long MILLIS_PER_HOUR = 60 * 60 * 1000;

    public static final float DAYS_PER_WEEK = 7.0F;

    private final float pal;

    private final int effort;

    private final int experience;

    private final String name;

    private final float duration;

    Activity(String name, float pal, int effort, int experience, float duration, int minLevel) {
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
        if (duration > MAX_DURATION) {
            duration = MAX_DURATION;
        }
        this.name = name;
        this.pal = pal;
        this.effort = effort;
        this.experience = experience;
        this.duration = duration;
    }

    Activity(String name, float pal, int effort, int experience, int minLevel) {
        this(name, pal, effort, experience, DEFAULT_DURATION, minLevel);
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
        return (long) (duration * MILLIS_PER_HOUR);
    }

    public int getAttraction(float buildUp) {
        return (int) (buildUp * effort * BASE_ATTRACTION_FROM_EFFORT + 1);
    }

    public abstract void perform(Body.Fitness fitness, BodyType bodyType);

    public abstract boolean isToDemanding();
}
