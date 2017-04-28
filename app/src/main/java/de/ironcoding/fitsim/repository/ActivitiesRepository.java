package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Type;

/**
 * Created by larsl on 27.04.2017.
 */

public class ActivitiesRepository extends BaseDaoRepository<List<Activity>, IActivitiesDao> {
    public ActivitiesRepository(IActivitiesDao activitiesDao) {
        super(activitiesDao);
    }

    public List<Activity> loadForLevel(Level level) {
        checkDao();
        return dao.loadForLevel(level);
    }

    public List<Activity> loadForTYpe(int typeId) {
        checkDao();
        return dao.loadForType(new Type(typeId, ""));
    }

    public List<Activity> loadForLevelAndType(Level level, int typeId) {
        checkDao();
        List<Activity> levelActivities = loadForLevel(level);
        return Filter.filterForType(levelActivities, new Type(typeId, ""));
    }

}
