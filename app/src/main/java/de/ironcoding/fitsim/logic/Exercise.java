package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Exercise extends Activity {

    private static final int TYPE_INT = 0;

    private final long strainedMuscle;

    public Exercise(String name, float pal, int effort, int experience, long strainedMuscle, int minLevel) {
        super(name, pal, effort, experience, minLevel, TYPE_INT);
        this.strainedMuscle = strainedMuscle;
    }

    @Override
    public void perform(Body.Fitness fitness, BodyType bodyType, Muscles muscles) {
        fitness.improveStrength(bodyType.getBuildUp());
        muscles.strain(strainedMuscle, getAttraction(bodyType.getBuildUp()));
    }

    @Override
    public boolean isToDemanding(Muscles muscles) {
        return !muscles.isDurable(strainedMuscle);
    }
}
