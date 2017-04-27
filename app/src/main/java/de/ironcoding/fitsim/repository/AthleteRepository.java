package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 18.04.2017.
 */

public class AthleteRepository implements IAthleteRepository {

    private IAthleteDao athleteDao;

    private Athlete athlete;

    public AthleteRepository(IAthleteDao athleteDao){
        this.athleteDao = athleteDao;
    }

    @Override
    public Athlete load(ISpecification specification) {
        if (athlete == null) {
            if (athleteDao == null) {
                throw new IllegalStateException("No IDao<Athlete> specified to load Athlete");
            }
            athlete = athleteDao.load();
        }
        return athlete == null ? null : athlete.copy();
    }

    public void updateAthlete(Athlete athlete) {
        if (athlete == null) {
            return;
        }
        this.athlete = athlete.copy();
        if (athleteDao != null ) {
            athleteDao.storeAthlete(athlete);
        }
    }
}
