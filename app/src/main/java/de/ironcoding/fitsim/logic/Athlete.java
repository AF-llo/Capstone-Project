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

    private Level level;

    private Body body;

    private boolean isBusy = false;

    private Athlete () {}

    public static Athlete buildNew(Body body) {
        return build(0, body);
    }

    public static Athlete build(int experience, Body body) {
        Athlete athlete = new Athlete();
        if (body == null) {
            throw new IllegalArgumentException("Your body was null. An athlets needs a body to train and do sport");
        }
        athlete.level = Level.create(experience);
        athlete.body = body;
        return athlete;
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

    // BEGIN all actions for athleteModel

    /**
     * The athlete will eat only when he is able to. This can be checked by calling {@link #canEat(Nutrition)}
     */
    public void eat(Nutrition nutrition) {
        if (!canEat(nutrition)) {
            return;
        }
        if (nutrition == null) {
            return;
        }
        body.digest(nutrition);
    }

    /**
     * The athlete will only perform this activity when is able to. This can be checked by {@link #isAbleToDo(Activity)}
     *
     */
    public void doActivity(Activity activity) {
        if (!isAbleToDo(activity)) {
            return;
        }
        gainExperience(activity.getExperience());
        body.performActivity(activity);
    }

    /**
     * This will release from beeing saturated which will force that the Athlete can eat new {@link Meal}.
     */
    public void goToRestRoom() {
        body.defecate();
    }

    /**
     * This will check if the ahtletes body can process this nutrition.
     */
    public boolean canEat(Nutrition nutrition) {
        return nutrition.isAccepted(body);
    }

    public void refreshBody() {
        body.refresh();
    }

    /**
     * This will improve every muscles state by one step.
     */
    public void relaxMuscles() {
        body.relaxAllMuscles();
    }

    /**
     * This will force all muscles to shrink. Also the bodies fitness will impair.
     */
    public void muscleBreakDown() {
        body.breakDown();
    }

    /**
     * When called, the athlet will be busy an not able to perfom further
     * Activities. Than {@link #setReady()} must be called to release this state
     */
    public void setBusy() {
        isBusy = true;
    }

    /**
     * This will enable the athlete to do activities again.
     */
    public void setReady() {
        isBusy = false;
    }

    public boolean isAbleToDo(Activity activity) {
        return !isBusy && activity != null && body.isAbleToDo(activity);
    }

    private void gainExperience(int experience) {
        level.gainExperience(experience);
    }
    
    // END

    public Athlete copy() {
        Athlete athlete = new Athlete();
        athlete.level = level.copy();
        athlete.body = body.copy();
        return athlete;
    }

}
