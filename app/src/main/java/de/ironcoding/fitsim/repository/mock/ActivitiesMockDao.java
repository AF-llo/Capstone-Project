package de.ironcoding.fitsim.repository.mock;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Cardio;
import de.ironcoding.fitsim.logic.Exercise;
import de.ironcoding.fitsim.repository.IActivitiesDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class ActivitiesMockDao implements IActivitiesDao {
    @Override
    public List<Activity> load() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Exercise("Chestpress", 1.4F, 20, 50, MockMuscles.CHEST, 1));
        activities.add(new Exercise("Pulldowns", 1.4F, 20, 50, MockMuscles.BACK, 1));
        activities.add(new Exercise("Scottcurls", 1.25F, 10, 30, MockMuscles.BICEPS, 1));
        activities.add(new Exercise("Pulldowns", 1.2F, 10, 30, MockMuscles.TRICEPS, 1));
        activities.add(new Exercise("Farmers Walk", 1.3F, 15, 35, MockMuscles.FOREARMS, 5));
        activities.add(new Cardio("Cycling", 1.5F, 20, 40, 1));
        activities.add(new Cardio("Rowing Machine", 1.6F, 25, 40, 5));
        return activities;
    }
}
