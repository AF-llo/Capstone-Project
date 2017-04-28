package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 18.04.2017.
 */

public class AthleteRepository extends BaseDaoRepository<Athlete, IAthleteDao> {

    private Athlete athlete;

    public AthleteRepository(IAthleteDao athleteDao) {
        super(athleteDao);
    }

    public Athlete loadAthlete() {
        if (athlete == null) {
            if (dao == null) {
                throw new IllegalStateException("No IDao<Athlete> specified to loadAthlete Athlete");
            }
            athlete = dao.load();
        }
        return athlete == null ? null : athlete.copy();
    }

    public void updateAthlete(Athlete athlete) {
        if (athlete == null) {
            return;
        }
        this.athlete = athlete.copy();
        if (dao != null ) {
            dao.storeAthlete(athlete);
        }
    }
}
