package de.ironcoding.fitsim.util;

/**
 * Created by larsl on 29.04.2017.
 */

public class GameTimeUtil {

    public static final float MAX_DURATION = 24.0F;

    public static final float HOURS_PER_GAME_DAY = 6.0F;

    /**
     * Relation of game hours to real hours
     */
    public static final float SCALED_GAME_TIME = HOURS_PER_GAME_DAY / MAX_DURATION;

    public static final long SECONDS_PER_HOUR = 60 * 60;

    public static final long MILLIS_PER_SECOND = 1000;

    public static final float DAYS_PER_WEEK = 7.0F;

    public static long  durationInMillis(float durationInRealHours) {
        return (long) (durationInRealHours * SCALED_GAME_TIME * GameTimeUtil.MILLIS_PER_SECOND * SECONDS_PER_HOUR);
    }

    public static long durationInSeconds(float durationInRealHours) {
        return (long) (durationInRealHours * SCALED_GAME_TIME * SECONDS_PER_HOUR);
    }

    public static int millisInSeconds(long millis) {
        return (int) (millis / (float) MILLIS_PER_SECOND);
    }
}
