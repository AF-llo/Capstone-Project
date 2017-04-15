package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Cardio extends Activity {
    public Cardio(float pal, int effort, int experience) {
        super(pal, effort, experience);
    }

    @Override
    public void perform(Body.Stats stats, Body.Fitness fitness) {
        // TODO: 15.04.2017
    }

    @Override
    public boolean isToDemanding() {
        return false;
    }
}
