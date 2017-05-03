package de.ironcoding.fitsim.logic;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 12.04.2017.
 */

public class Muscle {

    public static final int INITIAL_VOLUME = 50;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OVERLOADED, TIRED, DURABLE, RECOVERED})
    public @interface Condition {}

    public static final int OVERLOADED = 0;
    public static final int TIRED = 1;
    public static final int DURABLE = 2;
    public static final int RECOVERED = 3;

    private final long id;

    private final String name;

    private int volume;

    private int limit;

    private @Muscle.Condition int condition;

    Muscle(long id, String name, int volume, int limit, @Condition int condition) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.limit = limit;
        this.condition = condition;
    }

    public static Muscle build(long id, String name, int limit) {
        return reBuild(id, name, INITIAL_VOLUME, limit, RECOVERED);
    }

    public static Muscle reBuild(long id, String name, int volume, int limit, @Condition int condition) {
        if (name == null) {
            name = "";
        }
        if (limit < INITIAL_VOLUME) {
            limit = INITIAL_VOLUME;
        }
        if (volume < INITIAL_VOLUME) {
            volume = INITIAL_VOLUME;
        }
        if (volume > limit) {
            volume = limit;
        }
        if (condition < OVERLOADED) {
            condition = OVERLOADED;
        }
        if (condition > RECOVERED) {
            condition = RECOVERED;
        }
        return new Muscle(id, name, volume, limit, condition);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isDurable() {
        return condition != OVERLOADED;
    }

    public float getPercentualGrowing() {
        return (float) (volume - INITIAL_VOLUME) / (float) INITIAL_VOLUME;
    }

    public void strain(int attraction) {
        if (condition != OVERLOADED) {
            condition--;
        }
        grow(attraction);
    }

    public void relax() {
        if (condition != RECOVERED) {
            condition++;
        }
    }
    
    private void grow(int amount) {
        if (amount < 0) {
            amount = 0;
        }
        volume += amount;
        if (volume > limit) {
            volume = limit;
        }
    }
    
    public void shrink() {
        volume--;
        if (volume < INITIAL_VOLUME) {
            volume = INITIAL_VOLUME;
        }
    }

    public @Condition int getCondition() {
        return condition;
    }

    Muscle copy() {
        return new Muscle(id, name, volume, limit, condition);
    }
}
