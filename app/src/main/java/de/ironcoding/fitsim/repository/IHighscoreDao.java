package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 03.05.2017.
 */

public interface IHighscoreDao extends IDao<List<IHighscore>> {
    void replaceHighscores(List<IHighscore> highscores);
}
