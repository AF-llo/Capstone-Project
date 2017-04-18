package de.ironcoding.fitsim.repository;

import de.ironcoding.fitsim.logic.ILevelItem;
import de.ironcoding.fitsim.logic.Level;

/**
 * Created by larsl on 18.04.2017.
 */

public class LevelSpecification implements ISpecification<ILevelItem> {

    private final Level level;

    public LevelSpecification(Level level) {
        this.level = level;
    }

    @Override
    public boolean meetSpecification(ILevelItem data) {
        return level.getValue() >= data.getMinLevel();
    }
}
