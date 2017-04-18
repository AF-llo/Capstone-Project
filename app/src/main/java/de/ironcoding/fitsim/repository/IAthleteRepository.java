package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 18.04.2017.
 */

public interface IAthleteRepository extends IRepository<Athlete> {

    void updateAthlete(Athlete athlete);

}
