package de.ironcoding.fitsim.repository.db;

import de.ironcoding.fitsim.logic.Muscles;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.repository.IMusclesDao;

/**
 * Created by larsl on 30.04.2017.
 */

public class MuscleDbDao implements IMusclesDao {

    private DaoMaster daoMaster;

    public MuscleDbDao(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    @Override
    public Muscles load() {
        // TODO: 30.04.2017 load from db
        return null;
    }
}
