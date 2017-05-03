package de.ironcoding.fitsim.repository.db;

import java.util.ArrayList;
import java.util.List;

import de.ironcoding.fitsim.logic.IHighscore;
import de.ironcoding.fitsim.persistance.DaoMaster;
import de.ironcoding.fitsim.persistance.DaoSession;
import de.ironcoding.fitsim.persistance.model.DbHighscore;
import de.ironcoding.fitsim.repository.IHighscoreDao;

/**
 * Created by larsl on 03.05.2017.
 */

public class DbHighscoreDao implements IHighscoreDao {
    private DaoMaster daoMaster;

    public DbHighscoreDao(DaoMaster daoMaster) {
        this.daoMaster = daoMaster;
    }

    @Override
    public List<IHighscore> load() {
        DaoSession session = daoMaster.newSession();
        List<DbHighscore> highscores = session.getDbHighscoreDao().loadAll();
        List<IHighscore> loadedHighscores = new ArrayList<>();
        if (highscores != null) {
            for (DbHighscore highscore : highscores) {
                loadedHighscores.add(highscore);
            }
        }
        return loadedHighscores;
    }

    @Override
    public void replaceHighscores(List<IHighscore> highscores) {
        if (highscores == null || highscores.size() == 0) {
            return;
        }
        DaoSession session = daoMaster.newSession();
        session.getDbHighscoreDao().deleteAll();
        for (int i = 0; i < highscores.size(); i++) {
            IHighscore highscore = highscores.get(i);
            if (highscore != null) {
                DbHighscore dbHighscore = new DbHighscore();
                dbHighscore.setId(i);
                dbHighscore.setName(highscore.getName());
                dbHighscore.setPoints(highscore.getPoints());
                session.getDbHighscoreDao().insert(dbHighscore);
            }
        }
        session.clear();
    }
}
