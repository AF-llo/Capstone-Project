package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 18.04.2017.
 */

public interface IAthleteDao extends IDao<Athlete> {
    void storeAthlete(Athlete athlete);
}
