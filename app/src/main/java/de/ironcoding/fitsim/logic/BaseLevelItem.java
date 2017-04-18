package de.ironcoding.fitsim.logic;

/**
 * Created by larsl on 18.04.2017.
 */

public class BaseLevelItem implements ILevelItem {

    private int minLevel;

    public BaseLevelItem(int minLevel) {
        this.minLevel = minLevel;
    }

    @Override
    public int getMinLevel() {
        return minLevel;
    }
}
