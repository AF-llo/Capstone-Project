package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.Level;

/**
 * Created by larsl on 18.04.2017.
 */

public abstract class BaseLevelRepository<T> extends BaseDataRepository<T> {

    protected Level level;

    public BaseLevelRepository(IDao dao) {
        super(dao);
    }

    @Override
    public T load(ISpecification specification) {
        if (!(specification instanceof LevelSpecification)) {
            throw new IllegalArgumentException("BaseLevelRepository requires LevelSpecification!");
        }
        return loadByLevel((LevelSpecification) specification);
    }

    protected abstract T loadByLevel(LevelSpecification levelSpecification);
}
