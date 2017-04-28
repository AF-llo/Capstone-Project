package de.ironcoding.fitsim.repository;

/**
 * Created by larsl on 18.04.2017.
 */

public abstract class BaseDaoRepository<T, V extends IDao<T>> {

    protected final V dao;

    protected BaseDaoRepository(V dao) {
        this.dao = dao;
    }

}
