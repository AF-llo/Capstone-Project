package de.ironcoding.fitsim.repository.db;

import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.repository.IAthleteDao;
import de.ironcoding.fitsim.repository.IMusclesDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class AthleteDbDao implements IAthleteDao {

    private IMusclesDao musclesDao;

    private DaoMaster daoMaster;

    public AthleteDbDao(IMusclesDao musclesDao, DaoMaster master) {
        this.musclesDao = musclesDao;
        this.daoMaster = master;
    }

    @Override
    public Athlete load() {
        // TODO: 30.04.2017 load from db
        return null;
    }

    @Override
    public void storeAthlete(Athlete athlete) {
        // TODO: 30.04.2017 write to db (athlete and muscles)
    }
}
