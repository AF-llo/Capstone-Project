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

    public Athlete reloadAthlete() {
        athlete = null;
        return loadAthlete();
    }

    public Athlete loadAthlete() {
        if (athlete == null) {
            athlete = load();
        }
        return athlete.copy();
    }

    public void updateAthlete(Athlete athlete) {
        if (athlete == null) {
            return;
        }
        this.athlete = athlete.copy();
        checkDao();
        dao.storeAthlete(athlete);
    }
}
