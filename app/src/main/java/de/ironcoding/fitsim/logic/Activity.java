package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public abstract class Activity {

    private final float pal;

    private final int effort;

    private final int experience;

    public Activity(float pal, int effort, int experience) {
        if (pal < 0) {
            pal = 0;
        }
        if (effort < 0) {
            effort = 0;
        }
        if (experience < 0) {
            experience = 0;
        }
        this.pal = pal;
        this.effort = effort;
        this.experience = experience;
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

    public abstract void perform(Body.Stats stats, Body.Fitness fitness);

    public abstract boolean isToDemanding();
}
