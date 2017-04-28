package de.ironcoding.fitsim.logic;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

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

    public static Athlete buildNew(Body body, List<Muscle> muscles) {
        return build(0, body, muscles);
    }

    public static Athlete build(int experience, Body body, List<Muscle> muscles) {
        Athlete athlete = new Athlete();
        if (body == null) {
            throw new IllegalArgumentException("Your body was null. An athlets needs a body to train and do sport");
        }
        athlete.level = Level.create(experience);
        athlete.body = body;
        Muscles.buildUpMuscles(muscles);
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

    public List<Muscle> getMuscles() {
        return Muscles.get().getAll();
    }

    // BEGIN all actions for athleteModel

    public void eat(Nutrition nutrition) {
        if (!canEat()) {
            return;
        }
        if (nutrition == null) {
            return;
        }
        body.digest(nutrition);
    }

    public void doActivity(Activity activity, boolean isBusy) {
        if (!isAbleToDo(activity)) {
            return;
        }
        this.isBusy = isBusy;
        gainExperience(activity.getExperience());
        body.performActivity(activity);
    }

    public void goToRestRoom() {
        body.defecate();
    }

    public boolean canEat() {
        return body.canEat();
    }

    public void refreshBody() {
        body.refresh();
    }

    public void relaxMuscles() {
        Muscles.get().relaxAll();
    }

    public void muscleBreakDown() {
        Muscles.get().breakDown();
        body.breakDown();
    }

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
