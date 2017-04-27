package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Activity;

/**
 * Created by larsl on 27.04.2017.
 */

public class ActivitiesRepository extends LevelListRepository<Activity> {
    public ActivitiesRepository(IActivitiesDao activitiesDao) {
        super(activitiesDao);
    }
}
