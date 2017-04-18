package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;

/**
 * Created by larsl on 18.04.2017.
 */

public class AthleteRepository implements IAthleteRepository {

    private IAthleteDao athleteDao;

    private Athlete athlete;

    private boolean isInitialized;

    private AthleteRepository(){}

    public static void init(IAthleteDao athleteDao) {
        if (athleteDao == null) {
            throw new IllegalArgumentException("IAthleteDao may not be null!");
        }
        AthleteRepository athleteRepository = InstanceHolder.INSTANCE;
        athleteRepository.isInitialized = true;
    }

    public static AthleteRepository get() {
        AthleteRepository athleteRepository = InstanceHolder.INSTANCE;
        if (!athleteRepository.isInitialized) {
            throw new IllegalStateException("Please initialize AthleteRepository first!");
        }
        return athleteRepository;
    }

    @Override
    public Athlete load(ISpecification specification) {
        if (athlete == null) {
            if (athleteDao == null) {
                throw new IllegalStateException("No IDao<Athlete> specified to load Athlete");
            }
            athlete = athleteDao.load();
        }
        return athlete.copy();
    }

    public void updateAthlete(Athlete athlete) {
        if (athlete == null) {
            return;
        }
        this.athlete = athlete;
        if (athleteDao != null ) {
            athleteDao.storeAthlete(athlete);
        }
    }

    private static final class InstanceHolder {
        static AthleteRepository INSTANCE = new AthleteRepository();
    }
}
