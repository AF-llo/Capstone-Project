package de.ironcoding.fitsim.repository.db;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.logic.Body;
import de.ironcoding.fitsim.logic.BodyType;
import de.ironcoding.fitsim.logic.Calories;
import de.ironcoding.fitsim.logic.Level;
import de.ironcoding.fitsim.logic.Macros;
import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.persistance.DaoSession;
import de.ironcoding.fitsim.persistance.model.DbAthlete;
import de.ironcoding.fitsim.persistance.model.DbMuscle;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.IMusclesDao;
import de.ironcoding.fitsim.util.AppSettings;

/**
 * Created by larsl on 30.04.2017.
 */

public class DbAthleteDao implements IAthleteDao {

    private static final long DEFAULT_ATHLETE_ID = 1L;

    private IMusclesDao musclesDao;

    private DaoMaster daoMaster;

    private AppSettings appSettings;

    public DbAthleteDao(IMusclesDao musclesDao, DaoMaster master, AppSettings appSettings) {
        this.musclesDao = musclesDao;
        this.daoMaster = master;
        this.appSettings = appSettings;
    }

    @Override
    public Athlete load() {
        DaoSession session = daoMaster.newSession();
        DbAthlete dbAthlete = session.getDbAthleteDao().load(appSettings.getAthleteIdFromSettings());
        @BodyType.Name String bodyType = dbAthlete.getBodyType();
        BodyType type = BodyType.getType(bodyType);
        Body.Properties properties = new Body.Properties(dbAthlete.getGender(), dbAthlete.getSize(), dbAthlete.getAge());
        Body.Stats stats = new Body.Stats(dbAthlete.getEnergy(), dbAthlete.getWeight());
        Body body = Body.warmUp(
                type,
                properties,
                stats,
                new Body.Fitness(dbAthlete.getStrength(), dbAthlete.getStamina()),
                Calories.create(
                        type,
                        properties,
                        stats,
                        Macros.get(
                                dbAthlete.getProteinProportion(),
                                dbAthlete.getCarbsProportion(),
                                dbAthlete.getFatProportion(),
                                dbAthlete.getConsumedProteine(),
                                dbAthlete.getConsumedCarbs(),
                                dbAthlete.getConsumedFat()),
                        dbAthlete.getRequiredEnergy()),
                musclesDao.load());
        session.clear();
        return Athlete.build(dbAthlete.getExperience(), body);
    }

    @Override
    public void storeAthlete(Athlete athlete) {
        boolean newAthlete = false;
        DaoSession session = daoMaster.newSession();
        long athletId = appSettings.getAthleteIdFromSettings();
        DbAthlete dbAthlete = session.getDbAthleteDao().load(athletId);

        Body body = athlete.getBody();
        Level level = athlete.getLevel();
        Body.Properties properties = body.getProperties();
        Body.Stats stats = body.getStats();
        Body.Fitness fitness = body.getFitness();
        Calories calories = body.getCalories();
        Macros macros = calories.getMacros();

        if (dbAthlete == null) {
            newAthlete = true;
            dbAthlete = new DbAthlete();
            athletId = DEFAULT_ATHLETE_ID;
        }
        dbAthlete.setId(athletId);
        dbAthlete.setExperience(level.getTotalExperience());
        dbAthlete.setGender(properties.getGender());
        dbAthlete.setAge(properties.getAge());
        dbAthlete.setSize(properties.getSize());
        dbAthlete.setBodyType(body.getType().getName());
        dbAthlete.setEnergy(stats.getEnergy());
        dbAthlete.setWeight(stats.getWeight());
        dbAthlete.setStrength(fitness.getStrength());
        dbAthlete.setStamina(fitness.getStamina());
        dbAthlete.setRequiredEnergy(calories.getRequiredEnergy());
        dbAthlete.setProteinProportion(macros.getProteineProportion());
        dbAthlete.setCarbsProportion(macros.getCarbsProportion());
        dbAthlete.setFatProportion(macros.getFatProportion());
        dbAthlete.setConsumedProteine(macros.getConsumedProteine());
        dbAthlete.setConsumedCarbs(macros.getConsumedCarbs());
        dbAthlete.setConsumedFat(macros.getConsumedFat());

        Muscles muscles = body.getMuscles();
        for (Muscle muscle : muscles.getAll()) {
            DbMuscle dbMuscle = new DbMuscle();
            dbMuscle.setAthleteId(athletId);
            dbMuscle.setMuscleId(muscle.getId());
            dbMuscle.setVolume(muscle.getVolume());
            dbMuscle.setCondition(muscle.getCondition());
            session.getDbMuscleDao().insertOrReplace(dbMuscle);
        }
        session.getDbAthleteDao().insertOrReplace(dbAthlete);
        if (newAthlete) {
            appSettings.writeAthleteIdToSettings(athletId);
        }
    }
}
