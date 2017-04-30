package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.repository.initial.InitialAthleteDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class InitialAthleteRepository extends BaseDaoRepository<Athlete, InitialAthleteDao> {

    public InitialAthleteRepository(InitialAthleteDao dao) {
        super(dao);
    }

    public void createInitialAthlete(@BodyType.Name String bodyType, @Athlete.Gender int gender, int age, float size) {
        dao.setBodyType(bodyType);
        dao.setGender(gender);
        dao.setAge(age);
        dao.setSize(size);

        dao.storeAthlete(dao.load());
    }
}
