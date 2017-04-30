package de.ironcoding.fitsim.repository.mock;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.repository.IDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class MusclesMockDao implements IDao<Muscles> {
    @Override
    public Muscles load() {
        List<Muscle> muscles = new ArrayList<>();
        muscles.add(Muscle.build(MockMuscles.CHEST, "Chest", 100, 1));
        muscles.add(Muscle.build(MockMuscles.BACK, "Back", 100, 1));
        muscles.add(Muscle.build(MockMuscles.BICEPS, "Biceps", 75, 1));
        muscles.add(Muscle.build(MockMuscles.TRICEPS, "Triceps", 75, 1));
        muscles.add(Muscle.build(MockMuscles.FOREARMS, "Forearms", 70, 5));
        return Muscles.buildUpMuscles(muscles);
    }
}
