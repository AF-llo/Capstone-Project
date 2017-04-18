package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 12.04.2017.
 */

public class Cardio extends Activity {

    public Cardio(String name, float pal, int effort, int experience, int minLevel) {
        super(name, pal, effort, experience, minLevel);
    }

    @Override
    public void perform(Body.Fitness fitness, BodyType bodyType) {
        fitness.improveStamina(bodyType.getEndurance());
    }

    @Override
    public boolean isToDemanding() {
        // cardio is always possible when enough energy is availables
        return false;
    }
}
