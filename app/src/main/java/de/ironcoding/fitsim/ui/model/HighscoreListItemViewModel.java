package de.ironcoding.fitsim.ui.model;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 03.05.2017.
 */

public class HighscoreListItemViewModel {

    private IHighscore highscore;

    public HighscoreListItemViewModel(IHighscore highscore) {
        this.highscore = highscore;
    }

    public String getName() {
        return highscore.getName();
    }

    public String getPoints() {
        return String.valueOf(highscore.getPoints());
    }

    public long getId() {
        return highscore.getId();
    }
}
