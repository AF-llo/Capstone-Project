package de.ironcoding.fitsim.repository;

/**
 * Created by larsl on 18.04.2017.
 */

public interface IRepository<T> {
    T load(ISpecification specification);
}
