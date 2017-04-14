package de.ironcoding.fitsim.logic;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 12.04.2017.
 */

public class Athlete {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FEMALE, MALE})
    public @interface Gender {}
    public static final int FEMALE = 0;
    public static final int MALE = 1;

    private boolean isInitialized = false;

    private Level level;

    private Body body;

    private @Gender int gender;

    private Athlete () {}

    public static Athlete build(@Gender int gender, int experience, Body body) {
        if (body == null) {
            throw new IllegalArgumentException("Your body was null. An athlets needs a body to eat and do sport");
        }
        Athlete athlete = InstanceHolder.INSTANCE;
        if (athlete.isInitialized) {
            throw new IllegalStateException("Your athlete has already been build. Please call Athlete.build(...) only once");
        }
        athlete.gender = gender;
        athlete.level = Level.create(experience);
        athlete.body = body;
        athlete.isInitialized = true;
        return athlete;
    }

    public static Athlete get() {
        Athlete athlete = InstanceHolder.INSTANCE;
        if (!athlete.isInitialized) {
            throw new IllegalStateException("Your athelete is not ready yet. Please call Athlete.warmUp(...) once before usage!");
        }
        return InstanceHolder.INSTANCE;
    }

    /**
     * @return
     *              Copy of level to ensure immutability.
     */
    public Level getLevel() {
        return level.copy();
    }

    /**
     * @return
     *              Copy of body to ensure immutability.
     */
    public Body getBody() {
        return body.copy();
    }

    public @Gender int getGender() {
        return gender;
    }

    public void gainExperience(int experience) {
        level.gainExperience(experience);
    }

    public void eat(Food food) {
        body.digest(food);
    }

    public void doActivity(Activity activity) {
        body.performActivity(activity);
    }

    private static final class InstanceHolder {
        static final Athlete INSTANCE = new Athlete();
    }

}
