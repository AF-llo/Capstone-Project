package de.ironcoding.fitsim.repository.local;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.repository.BaseDaoRepository;

/**
 * Created by larsl on 30.04.2017.
 */

public class LocalAthleteRepository extends BaseDaoRepository<Athlete, LocalAthleteDao> {

    public LocalAthleteRepository(LocalAthleteDao dao) {
        super(dao);
    }

    public Athlete createInitialAthlete(@BodyType.Name String bodyType, @Athlete.Gender int gender, int age, float size) {
        dao.setBodyType(bodyType);
        dao.setGender(gender);
        dao.setAge(age);
        dao.setSize(size);

        Athlete athlete = dao.load();
        dao.storeAthlete(athlete);
        return athlete;
    }
}
