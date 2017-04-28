package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.Activity;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Type;

/**
 * Created by larsl on 27.04.2017.
 */

public interface IActivitiesDao extends IDao<List<Activity>> {
    List<Activity> loadForLevel(Level level);
    List<Activity> loadForType(Type type);
}
