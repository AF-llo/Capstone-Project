package de.ironcoding.fitsim.repository;

import java.util.List;

import de.ironcoding.fitsim.logic.IHighscore;

/**
 * Created by larsl on 03.05.2017.
 */

public class HighscoreRepository extends BaseDaoRepository<List<IHighscore>, IHighscoreDao> {
    public HighscoreRepository(IHighscoreDao dao) {
        super(dao);
    }

    public List<IHighscore> loadHighscore() {
        return dao.load();
    }

    public void replaceHighscores(List<IHighscore> highscores) {
        dao.replaceHighscores(highscores);
    }
}
