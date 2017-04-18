package de.ironcoding.fitsim.repository;

/**
 * Created by larsl on 18.04.2017.
 */

public abstract class BaseDaoRepository<T> implements IRepository<T> {

    protected final IDao<T> dao;

    protected BaseDaoRepository(IDao<T> dao) {
        this.dao = dao;
    }



}
