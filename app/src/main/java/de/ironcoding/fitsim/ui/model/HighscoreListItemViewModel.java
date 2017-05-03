package de.ironcoding.fitsim.ui.model;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 03.05.2017.
 */

public class HighscoreListItemViewModel {

    private IHighscore highscore;

    private long id;

    public HighscoreListItemViewModel(IHighscore highscore, long id) {
        this.highscore = highscore;
        this.id = id;
    }

    public String getName() {
        return highscore.getName();
    }

    public String getPoints() {
        return String.valueOf(highscore.getPoints());
    }

    public long getId() {
        return id;
    }
}
