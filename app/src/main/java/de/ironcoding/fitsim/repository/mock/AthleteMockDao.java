package de.ironcoding.fitsim.repository.mock;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.IAthleteDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class AthleteMockDao implements IAthleteDao {
    @Override
    public Athlete load() {
        // TODO: 18.04.2017  maybe add base dao
        return null;
    }

    @Override
    public void storeAthlete(Athlete athlete) {
        // TODO: 18.04.2017
    }
}
