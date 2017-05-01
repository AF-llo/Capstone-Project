package de.ironcoding.fitsim.repository.local;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.logic.Calories;
import de.ironcoding.fitsim.repository.IAthleteDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class LocalAthleteDao implements IAthleteDao {

    private IAthleteDao athlelteDbDao;

    private LocalMuscleDao initialMuscleDao;

    private int gender;

    private float size;

    private int age;

    private String bodyType;

    public LocalAthleteDao(IAthleteDao athlelteDbDao, LocalMuscleDao initialMuscleDao) {
        this.athlelteDbDao = athlelteDbDao;
        this.initialMuscleDao = initialMuscleDao;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public Athlete load() {
        BodyType type = BodyType.getType(bodyType);
        Body.Stats stats = new Body.Stats(
                Body.MAX_ENERGY,
                gender == Athlete.MALE ? Body.INITIAL_WEIGHT_MALE : Body.INITIAL_WEIGHT_FEMALE);
        Body.Properties properties = new Body.Properties(gender, size, age);
        return Athlete.build(0, Body.warmUp(
                type,
                properties,
                stats,
                new Body.Fitness(Body.INITIAL_FITNESS, Body.INITIAL_FITNESS),
                Calories.createDefault(
                        type,
                        properties,
                        stats),
                initialMuscleDao.load()));
    }

    @Override
    public void storeAthlete(Athlete athlete) {
        athlelteDbDao.storeAthlete(athlete);
    }
}
