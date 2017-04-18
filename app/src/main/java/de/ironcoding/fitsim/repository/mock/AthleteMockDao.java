package de.ironcoding.fitsim.repository.mock;

import java.util.List;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.IDao;

/**
 * Created by larsl on 18.04.2017.
 */

public class AthleteMockDao implements IAthleteDao {

    private IDao<List<Muscle>> muscleDao;

    public AthleteMockDao(IDao<List<Muscle>> muscleDao) {
        this.muscleDao = muscleDao;
    }

    @Override
    public Athlete load() {
        if (muscleDao == null) {
            return null;
        }
        return Athlete.buildNew(Body.warmUpAverageMale(BodyType.ENDOMORPH), muscleDao.load());
    }

    @Override
    public void storeAthlete(Athlete athlete) {
        // does not store data
    }
}
