package de.ironcoding.fitsim.repository.mock;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Cardio;
import de.ironcoding.fitsim.logic.Exercise;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Type;
import de.ironcoding.fitsim.repository.Filter;
import de.ironcoding.fitsim.repository.IActivitiesDao;
import de.ironcoding.fitsim.util.IconUtil;

/**
 * Created by larsl on 18.04.2017.
 */

public class ActivitiesMockDao implements IActivitiesDao {
    @Override
    public List<Activity> load() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Exercise(IconUtil.ID_BENCHPRESS, "Chestpress", 1.4F, 20, 50, MockMuscles.CHEST, 1));
        activities.add(new Exercise(IconUtil.ID_PULLDOWNS, "Pulldowns", 1.4F, 20, 50, MockMuscles.BACK, 1));
        activities.add(new Exercise(IconUtil.ID_SCOTTCURLS, "Scottcurls", 1.25F, 10, 30, MockMuscles.BICEPS, 1));
        activities.add(new Cardio(IconUtil.ID_CYCLING, "Cycling", 1.5F, 20, 500, 1));
        activities.add(new Cardio(IconUtil.ID_ROWING, "Rowing Machine", 1.6F, 25, 40, 5));
        return activities;
    }

    @Override
    public List<Activity> loadForLevel(Level level) {
        return Filter.filterForLevel(load(), level);
    }

    @Override
    public List<Activity> loadForType(Type type) {
        return Filter.filterForType(load(), type);
    }
}
