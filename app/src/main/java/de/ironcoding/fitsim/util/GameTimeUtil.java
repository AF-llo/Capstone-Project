package de.ironcoding.fitsim.util;

/**
 * Created by larsl on 29.04.2017.
 */

public class GameTimeUtil {

    public static final float MAX_DURATION = 24.0F;

    /**
     * Relation of game hours to real hours
     */
    public static final float SCALED_GAME_TIME = 6 / MAX_DURATION;

    public static final long MILLIS_PER_HOUR = 60 * 60 * 1000;

    public static final float DAYS_PER_WEEK = 7.0F;

    public static long  durationInMillis(float durationInRealHours) {
        return (long) (durationInRealHours * SCALED_GAME_TIME * GameTimeUtil.MILLIS_PER_HOUR);
    }
}
