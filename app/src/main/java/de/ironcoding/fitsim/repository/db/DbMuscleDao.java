package de.ironcoding.fitsim.repository.db;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.Muscle;
import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.persistance.DaoSession;
import de.ironcoding.fitsim.persistance.model.DbMuscle;
import de.ironcoding.fitsim.repository.IMusclesDao;
import de.ironcoding.fitsim.repository.local.LocalMuscleDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class DbMuscleDao implements IMusclesDao {

    private DaoMaster daoMaster;

    private LocalMuscleDao localMuscleDao;

    public DbMuscleDao(DaoMaster daoMaster, LocalMuscleDao localMuscleDao) {
        this.daoMaster = daoMaster;
        this.localMuscleDao = localMuscleDao;
    }

    @Override
    public Muscles load() {
        DaoSession session = daoMaster.newSession();
        List<DbMuscle> storedVolumes = session.getDbMuscleDao().loadAll();
        Muscles muscles = localMuscleDao.load();
        List<Muscle> musclesList = new ArrayList<>();
        for (DbMuscle storedVolume : storedVolumes) {
            Muscle localMuscle = muscles.getMuscle(storedVolume.getMuscleId());
            musclesList.add(Muscle.reBuild(
                    localMuscle.getId(),
                    localMuscle.getName(),
                    storedVolume.getVolume(),
                    localMuscle.getLimit(),
                    storedVolume.getCondition()));
        }
        session.clear();
        return Muscles.buildUpMuscles(musclesList);
    }
}
